package geometry;

import java.util.ArrayList;

/**
 * The object "Rectangle".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class Rectangle {
    // The width and height of the rectangle.
    private double width;
    private double height;
    // The upper left corner of the rectangle.
    private Point upperLeft;

    /**
     * Constructor of "Rectangle".
     *
     * @param upperLeft The upper left corner of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line The given line.
     * @return A List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] sides = {this.getUpperSide(), this.getBottomSide(), this.getLeftSide(), this.getRightSide()};
        java.util.List<Point> pointsList = new ArrayList<Point>();
        for (Line side : sides) {
            Point intersectionP = line.intersectionWith(side);
            if (intersectionP != null) {
                pointsList.add(intersectionP);
            }
        }
        return pointsList;
    }

    /**
     * Get the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get the upper left corner of the rectangle.
     *
     * @return  the upper left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Get the upper side of the rectangle.
     *
     * @return the upper side of the rectangle.
     */
    public Line getUpperSide() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, upperRight);
    }

    /**
     * Get the bottom side of the rectangle.
     *
     * @return the bottom side of the rectangle.
     */
    public Line getBottomSide() {
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(bottomLeft, bottomRight);
    }

    /**
     * Get the left side of the rectangle.
     *
     * @return the left side of the rectangle.
     */
    public Line getLeftSide() {
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(this.upperLeft, bottomLeft);
    }

    /**
     * Get the right side of the rectangle.
     *
     * @return the right side of the rectangle.
     */
    public Line getRightSide() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(upperRight, bottomRight);
    }

    /**
     * Convert this rectangle into a string:
     * "topLeft:(x1,y1) topRight:(x2,y2) bottomLeft:(x3,y3) bottomRight:(x4,y4)".
     *
     * @return A string describing this rectangle.
     */
    public String toString() {
        return ("top:" + this.getUpperSide().toString() + " bottom:" + this.getBottomSide().toString()
                + " left:" + this.getLeftSide().toString() + " right:" + this.getRightSide().toString());
    }
}
