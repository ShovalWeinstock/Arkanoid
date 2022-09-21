package general;

import geometry.Point;

/**
 * The object "velocity".
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 11.4.20
 */
public class Velocity {
    // The change in position on the X axis.
    private double dx;
    // The change in position on the Y axis.
    private double dy;

    /**
     * The constructor of "Velocity".
     *
     * @param dx Detla X, the change in position on the X axis.
     * @param dy Detla Y, the change in position on the Y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p The given point (with position (x,y)).
     * @return A new point, with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    /**
     * Creates a velocity given an angle and a speed.
     *
     * @param angle The given angle (in degrees) for the velocity.
     * @param speed The given speed for the velocity.
     * @return New velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle);
        double dx = Math.sin(radianAngle) * speed;
        double dy = -(Math.cos(radianAngle) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * Get the dx of this velocity.
     *
     * @return The dx of this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get the dy of this velocity.
     *
     * @return The dy of this velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Check if this velocity is equal to the given velocity "other".
     *
     * @param other The other velocity.
     * @return "true" if the velocities are equal, and "false" otherwise.
     */
    public boolean equals(Velocity other) {
        return (this.dx == other.getDx()) && (this.dy == other.getDy());
    }
}
