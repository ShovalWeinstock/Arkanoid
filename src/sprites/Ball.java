package sprites;

import biuoop.DrawSurface;
import gamelogic.CollisionInfo;
import levels.GameLevel;
import gamelogic.GameEnvironment;
import general.HelpingMethods;
import general.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.Random;

/**
 * The object "ball".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 11.4.21
 */
public class Ball implements Sprite {
    // The default speed
    static final double DEFAULT_SPEED = 50;
    // The minimum size (radius) of the slowest ball.
    static final int SLOWEST_BALL_SIZE = 50;

    // The size (radius) of the ball.
    private int size;
    // The color of he ball.
    private java.awt.Color color;
    // The location of the ball (its center point).
    private Point location;
    // The velocity of the ball. The default velocity is: dx=0, dy=0.
    private Velocity velocity = new Velocity(0, 0);
    // The height and width of the frame where the ball is moving.
    // The default height and width are 0 (when they are not set).
    private double frameHeight = 0;
    private double frameWidth = 0;
    // The top left corner of the frame where the ball is moving. The default point is (0,0).
    private Point frameCorner = new Point(0, 0);
    private GameEnvironment gameEnvironment;

    /**
     * A constructor of "Ball".
     * Creates a ball given its center point, its radius and its color.
     *
     * @param center The center of the ball.
     * @param r The radius (size) of the ball.
     * @param color The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.size = r;
        this.location = center;
        this.color = color;
    }

    /**
     * Creates a ball given the x and y values of its center point,
     * its radius and its color.
     *
     * @param x The x value of the center of the ball.
     * @param y The y value of the center of the ball.
     * @param r The radius (size) of the ball.
     * @param color The color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.size = r;
        this.location = new Point(x, y);
        this.color = color;
    }

    /**
     * Creates a ball given the x and y values of its center point,
     * its radius and its color.
     *
     * @param x The x value of the center of the ball.
     * @param y The y value of the center of the ball.
     * @param r The radius (size) of the ball.
     * @param color The color of the ball.
     * @param env The game environment of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment env) {
        this.size = r;
        this.location = new Point(x, y);
        this.color = color;
        this.gameEnvironment = env;
    }

    /**
     * Get the x value of the center of the ball.
     *
     * @return The x value of this point.
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * Get the y value of the center of the ball.
     *
     * @return The y value of this point.
     */
    public int getY() {
        return (int) this.location.getY();
    }

    /**
     * Get the size of the ball.
     *
     * @return The size of the ball.
     */
    public int getSize() { //double?????????????
        return this.size;
    }

    /**
     * Get the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Get the location of the ball.
     *
     * @return The location of the ball.
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface The given surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.size);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.size);

    }

    /**
     * Set the velocity of the ball.
     *
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set the velocity of the ball by given dx and dy.
     *
     * @param dx The change in position on the X axis.
     * @param dy The change in position on the Y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Set the height of the frame where the ball moves.
     *
     * @param height The height of the frame.
     */
    public void setFrameHeight(double height) {
        this.frameHeight = height;
    }

    /**
     * Set the width of the frame where the ball moves.
     *
     * @param width The width of the frame.
     */
    public void setFrameWidth(double width) {
        this.frameWidth = width;
    }

    /**
     * Set the corner (the top left corner) of the frame where the ball moves.
     *
     * @param corner The top left corner of the frame.
     */
    public void setFrameCorner(Point corner) {
        this.frameCorner = corner;
    }

    /**
     * Set a new location to the ball.
     *
     * @param x The X value of the new location.
     * @param y The Y value of the new location.
     */
    private void changeLocation(double x, double y) {
        this.location = new Point(x, y);
    }

    /**
     * Move the ball one step (no blocks in the surface).
     */
    public void moveOneStepNoBlocks() {
        // move the ball
        this.location = this.getVelocity().applyToPoint(this.location);
        // If a frame was set - make sure the new location is fully in the frame.
        if (frameWidth != 0 && frameHeight != 0) {
            double maxHeightY = frameCorner.getY() + this.frameHeight - this.size;
            double minHeightY = frameCorner.getY() + this.size;
            double maxWidthX = frameCorner.getX() + this.frameWidth - this.size;
            double minWidthX = frameCorner.getX() + this.size;
            boolean outOfFrame = false;
            // If the new location isn't fully in the frame - modify the velocity accordingly.
            if (this.location.getX() >= maxWidthX || this.location.getX() <= minWidthX) {
                this.setVelocity(-1 * this.velocity.getDx(), this.velocity.getDy());
                outOfFrame = true;
            }
            if (this.location.getY() >= maxHeightY || this.location.getY() <= minHeightY) {
                this.setVelocity(this.velocity.getDx(), -1 * this.velocity.getDy());
                outOfFrame = true;
            }
            // If necessary - get the ball into the frame.
            if (outOfFrame) {
                this.getInFrame();
            }
        }
    }

    /**
     * Move the ball one step.
     */
    public void moveOneStep() {
        // The potential location after the step.
        Point potentialLocation = this.getVelocity().applyToPoint(this.location);
        // potential location after two steps.
        Point locationForCheck = this.getVelocity().applyToPoint(potentialLocation);
        // The trajectory (how the ball will move without any obstacles).
        Line trajectory = new Line(this.location, locationForCheck);
        // Check for possible collision.
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        // If there is no collision.
        if (collision == null) {
            this.location = potentialLocation;
            return;
        }
        // If there is a collision move the ball to "almost" the hit point, but just slightly before it.
        double stoppingPointX = collision.collisionPoint().getX();
        double stoppingPointY = collision.collisionPoint().getY();
        Rectangle collisionRectangle = collision.collisionObject().getCollisionRectangle();
        if (collisionRectangle.getUpperSide().isInSegment(collision.collisionPoint())) {
            stoppingPointY -= this.size;
        } else if (collisionRectangle.getBottomSide().isInSegment(collision.collisionPoint())) {
            stoppingPointY += this.size;
        }
        if (collisionRectangle.getRightSide().isInSegment(collision.collisionPoint())) {
            stoppingPointX += this.size;
        } else if (collisionRectangle.getLeftSide().isInSegment(collision.collisionPoint())) {
            stoppingPointX -= this.size;
        }
        this.location = new Point(stoppingPointX, stoppingPointY);
        this.velocity = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);
    }

    /**
     * If the ball is out of the frame, get it into the frame.
     */
    public void getInFrame() {
        double maxHeightY = frameCorner.getY() + this.frameHeight - this.size;
        double minHeightY = frameCorner.getY() + this.size;
        double maxWidthX = frameCorner.getX() + this.frameWidth - this.size;
        double minWidthX = frameCorner.getX() + this.size;
        if (this.getX() > maxWidthX) {
            this.changeLocation(maxWidthX, this.getY());
        } else if (this.getX() < minWidthX) {
            this.changeLocation(minWidthX, this.getY());
        }
        if (this.getY() > maxHeightY) {
            this.changeLocation(this.getX(), maxHeightY);
        } else if (this.getY() < minHeightY) {
            this.changeLocation(this.getX(), minHeightY);
        }
    }

    /**
     * Create a random ball (random color and velocity),
     * given the ball size, the width and height of the frame,
     * and the top-left corner of the frame.
     *
     * @param ballSize The size of the ball (radius)
     * @param frameWidth The width of th frame.
     * @param frameHeight The height of the frame.
     * @param frameCorner The corner of the frame.
     * @return The new ball.
     */
    public static Ball createRandomBall(int ballSize, int frameWidth, int frameHeight, Point frameCorner) {
        Random rand = new Random(); // create a random-number generator
        // set ball location
        int x = rand.nextInt((int) frameCorner.getX() + frameWidth) + 1; // get x in the frame
        int y = rand.nextInt((int) frameCorner.getY() + frameHeight) + 1; // get y in the frame
        // if the ball is too big for the frame, modify its size accordingly.
        int size = ballSize;
        if (ballSize >= frameHeight / 2 || ballSize >= frameWidth / 2) {
            size = (Math.min(frameHeight, frameWidth)) / 2;
        }
        // create the ball
        Ball newBall = new Ball(x, y, size, HelpingMethods.getRandomColor());
        // set ball frame corner
        newBall.setFrameCorner(frameCorner);
        // set ball velocity
        double velocityAngle = rand.nextInt(360) + 1; // random angle (1-360)
        double speed;
        if (ballSize >= SLOWEST_BALL_SIZE) {
            speed = DEFAULT_SPEED / SLOWEST_BALL_SIZE;
        } else {
            speed = DEFAULT_SPEED / ballSize;
        }
        newBall.setVelocity(Velocity.fromAngleAndSpeed(velocityAngle, speed));
        // set ball frame
        newBall.setFrameWidth(frameWidth);
        newBall.setFrameHeight(frameHeight);
        newBall.getInFrame();
        return newBall;
    }

    /**
     * Set the game environment of the ball.
     * @param env the game environment.
     */
    public void setGameEnvironment(GameEnvironment env) {
        this.gameEnvironment = env;
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball from the given game.
     *
     * @param g The given game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}