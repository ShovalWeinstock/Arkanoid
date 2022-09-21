package geometry;

/**
 * The object "point".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 11.4.21
 */
public class Point {
    static final double EPSILON = Math.pow(10, -10);

    // The X value of the point.
    private double x;
    // The Y value of the point.
    private double y;

    /**
     * The constructor of "Point".
     * @param x The x value of the new point.
     * @param y The y value of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the distance of this point, to the given point "other".
     *
     * @param other The other point.
     * @return The distance of this point, to the given point "other".
     */
    public double distance(Point other) {
        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.x;
        double y2 = other.y;
        double distancePow2 = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        return Math.sqrt(distancePow2);
    }

    /**
     * Check if this point is equal to the given point "other".
     *
     * @param other The other point.
     * @return "true" if this point is equal to the point "other",
     * and "false" otherwise.
     */
    public boolean equals(Point other) {
        boolean xIsEqual = Math.abs(this.x - other.x) < EPSILON;
        boolean yIsEqual = Math.abs(this.y - other.y) < EPSILON;
        return (xIsEqual && yIsEqual);
    }

    /**
     * Get the x value of this point.
     *
     * @return The x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y value of this point.
     *
     * @return The y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Convert this point into a string.
     *
     * @return A string describing this point.
     */
    public String toString() {
        String pointStr = "(" + this.getX() + "," + this.getY() + ")";
        return pointStr;
    }

}
