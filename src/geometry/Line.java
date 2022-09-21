package geometry;

/**
 * The object "line".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 11.4.20
 */
public class Line {
    // Comparison threshold for equality of lines lengths.
    static final double EPSILON = Math.pow(10, -10);

    // The start point of the line.
    private final Point start;
    // The end point of the line.
    private final Point end;

    /**
     * A constructor of "Line".
     * Creates a line given its start point and its end point.
     *
     * @param start The starting point of the new line.
     * @param end The ending point of the new line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A constructor of "Line".
     * Creates a line given its start point x and y values,
     * and its end point x and y values.
     *
     * @param x1 The x value of the starting point of the new line.
     * @param y1 The y value of starting point of the new line.
     * @param x2 The x value of the ending point of the new line.
     * @param y2 he y value of the ending point of the new line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Get the length of this line.
     *
     * @return The length of this line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Get the middle point of this line.
     *
     * @return The middle point of this line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Get the start point of this line.
     *
     * @return The start point of this line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Get the end point of this line.
     *
     * @return The end point of this line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check if this line is intersecting with the given line "other".
     *
     * @param other the other line.
     * @return "true" if the lines intersect, and "false" otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point intersectionP = this.intersectionWith(other);
        if (intersectionP == null) {
            return false;
        }
        return true;
    }

    /**
     * Get the intersection point of this line (segment),
     * and the given line (segment) "other".
     *
     * @param other The other line (segment).
     * @return The intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersectionP = this.intersectionPoint(other);
        // if the linear equations don't have intersection point
        if (intersectionP == null) {
            return null;
        }
        // if the intersection point is on both segments
        if (this.isInSegment(intersectionP) && other.isInSegment(intersectionP)) {
            return intersectionP;
        }
        return null;
    }

    /**
     * Check if this line is equal to the given line "other".
     *
     * @param other The other line.
     * @return "true" if the lines are equal, and "false" otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        if ((this.start.equals(other.end)) && (this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * Get the intersection point of this line (its linear equation, not only a segment),
     * and the given line (its linear equation, not only a segment) "other".
     *
     * @param other The other line.
     * @return The intersection point if the linear equations intersect, and null otherwise.
     */
    //change to private
    public Point intersectionPoint(Line other) {
        //if the lines intersect in the edge ("continue each other")
        Point edgeIntersectionPoint = this.edgeIntersection(other);
        if (edgeIntersectionPoint != null) {
            return edgeIntersectionPoint;
        }
        //if one of the lines is a single point
        if (this.isSinglePoint()) {
            return this.intersectionOneSinglePoint(other);
        }
        if (other.isSinglePoint()) {
            return other.intersectionOneSinglePoint(this);
        }
        //otherwise:
        boolean isThisLineVertical = this.isVertical();
        boolean isOtherLineVertical = other.isVertical();
        //if both lines are vertical to the X axis (and the don't intersect in the edge),
        //they are parallel or overlap. they don't have a single intersection point.
        if (isThisLineVertical && isOtherLineVertical) {
            return null;
        }
        // if only this line is vertical to the X axis
        if (isThisLineVertical) {
            return this.intersectionOneVertical(other);

            //if only the other line is vertical to the X axis
        } else if (isOtherLineVertical) {
            return other.intersectionOneVertical(this);

            //if none of the lines is vertical to the X axis
        } else {
            double x;
            double y;
            double m1 = this.findSlope();
            double m2 = other.findSlope();
            double b1 = this.findYItercept();
            double b2 = other.findYItercept();
            //if both lines has the same slope (and the don't intersect in the edge),
            //they are parallel or overlap. they don't have a single intersection point.
            if (m1 == m2) {
                return null;
            }
            //otherwise:
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
            return new Point(x, y);
        }
    }

    /**
     * Check of the given point "p" is in this line (segment).
     *
     * @param p the given point
     * @return "true" if the point is in the segment, and "false" otherwise.
     */
    public boolean isInSegment(Point p) {
        double distancesSum = this.start.distance(p) + this.end.distance(p);
        double length = this.length();
        /*
         * return if "distancesSum" - "length" is closer to 0 than epsilon.
         * it is is, they are probably equal.
         */
        return (Math.abs(distancesSum - length) < EPSILON);
    }

    /**
     * Check if this line is vertical to the X axis.
     *
     * @return "true" if this line is vertical to the X axis, and "false" otherwise.
     */
    private boolean isVertical() {
        return (this.start.getX() == this.end.getX());
    }

    /**
     * Check if this line is only a single point.
     *
     * @return "true" if this line is a single point, and "false" otherwise.
     */

    private boolean isSinglePoint() {
        return this.start.equals(this.end);
    }

    /**
     * Get the intersection point of this line and the given line "other",
     * if this line is vertical to the X axis, and the given line "other" is not.
     *
     * @param other The other line.
     * @return The intersection point of this line and the given line "other".
     */
    private Point intersectionOneVertical(Line other) {
        double x;
        double y;
        x = this.start.getX();
        double m2 = other.findSlope();
        double b2 = other.findYItercept();
        y = m2 * x + b2;
        return new Point(x, y);
    }

    /**
     * Get the intersection point of this line and the given line "other",
     * if this line is a single point, and the given line "other" is not.
     *
     * @param other The other line.
     * @return The intersection point if the lines intersect, and null otherwise.
     */

    private Point intersectionOneSinglePoint(Line other) {
        if (other.isInSegment(this.start)) {
            return this.start;
        }
        return null;
    }

    /**
     * Get the intersection point of this line and the given line "other",
     * when both lines are vertical to the X axis, or have the same slope.
     * In this case, the intersection point is on the lines edges (they "continue each other").
     *
     * @param other The other line.
     * @return The intersection point if the lines intersect,
     * and null otherwise (they are parallel or overlap).
     */
    private Point edgeIntersection(Line other) {
        if (this.start.equals(other.end())) {
            if (!this.isInSegment(other.start()) && !other.isInSegment(this.end)) {
                return this.start;
            }
        }
        if (this.start.equals(other.start())) {
            if (!this.isInSegment(other.end()) && !other.isInSegment(this.end)) {
                return this.start;
            }
        }
        if (this.end.equals(other.start())) {
            if (!this.isInSegment(other.end()) && !other.isInSegment(this.start)) {
                return this.end;
            }
        }
        if (this.end.equals(other.end())) {
            if (!this.isInSegment(other.start()) && !other.isInSegment(this.start)) {
                return this.end;
            }
        }
        // Otherwise, they are parallel or overlap
        return null;
    }

    /**
     * Get the slope of this line.
     * If the line is vertical to the X axis, the slope is not defined,
     * and the method returns positive infinity.
     *
     * @return The slope of this line.
     */
    private double findSlope() {
        if (this.isVertical()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Get the Y-itercept (the "b" in the linear equation: y = ax + b), of this line.
     * If the line is vertical to the X axis, the Y-itercept doesn't exist,
     * and the method returns "NaN".
     *
     * @return The Y-itercept of this line.
     */
    private double findYItercept() {
        if (this.isVertical()) {
            return Double.NaN;
        }
        double slope = this.findSlope();
        return -1 * slope * this.start.getX() + this.start.getY();
    }

    /**
     * Return the closest intersection point of the given rectangle to the start of the line.
     * If this line does not intersect with the rectangle, return null.
     *
     * @param rect The given rectangle.
     * @return The closest intersection point of the rectangle to the start of the line.
     * and null if this line does not intersect with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> pointsList = rect.intersectionPoints(this);
        int pointsListSize = pointsList.size();
        // If there are no intersection points between the rectangle and the line.
        if (pointsListSize == 0) {
            return null;
        }
        // There are 1 or 2 intersection points between the line and the rectangle.
        Point closestPoint = pointsList.get(0);
        double smallestDistance = this.start.distance(closestPoint);
        if (pointsListSize == 2) {
            if (this.start.distance(pointsList.get(1)) < smallestDistance) {
                closestPoint = pointsList.get(1);
            }
        }
        return closestPoint;
    }

    /**
     * Convert this line into a string:
     * "start:(x1,y1) end:(x2,y2)", when (x1,y1) is the starting point of the line,
     * and (x2,y2) is the ending point.
     *
     * @return A string describing this line.
     */
    public String toString() {
        return ("start: " + this.start.toString() + " end: " + this.end.toString());
    }
}
