package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.Collidable;
import levels.GameLevel;
import general.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The object "Paddle" (The player in the game).
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class Paddle implements Sprite, Collidable {
    // Default step of the paddle.
    static final int DEFAULT_DELTA_X = 4;
    // The number of regions on the paddle upper side.
    static final int REGIONS_NUM = 5;
    // Paddle size.
    public static final int DEFAULT_WIDTH = 90;
    public static final int DEFAULT_HEIGHT = 20;
    // Paddle start location.
    public static final Point DEFAULT_START_POINT = new Point(400 - 0.5 * DEFAULT_WIDTH, 545);
    // Angles of the velocity after hitting the upper side of the paddle
    static final int LEFT_ANGLE = 300;
    static final int DELTA_ANGLE = 30;

    private Rectangle shape;
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color color;
    private int gameFrameThickness;
    private int gameGuiWidth;
    private int deltaX;

    /**
     * A constructor if "Paddle".
     *
     * @param newKeyboard The newKeyboard of the paddle.
     * @param color The color of the paddle.
     * @param guiWidth The width of the gui of the game.
     * @param frameThickness The thickness of the frame of the game.
     * */
    public Paddle(KeyboardSensor newKeyboard, java.awt.Color color, int guiWidth, int frameThickness) {
        this.shape = new Rectangle(DEFAULT_START_POINT, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.color = color;
        this.keyboard = newKeyboard;
        this.gameFrameThickness = frameThickness;
        this.gameGuiWidth = guiWidth;
        this.deltaX = DEFAULT_DELTA_X;
    }

    /**
     * A constructor if "Paddle".
     *
     * @param newKeyboard The newKeyboard of the paddle.
     * @param color The color of the paddle.
     * @param guiWidth The width of the gui of the game.
     * @param startPoint The start point od the paddle.
     * @param frameThickness The thickness of the frame of the game.
     * @param deltaX The speed of the paddle.
     * @param width The width of the paddle.
     * */
    public Paddle(KeyboardSensor newKeyboard, java.awt.Color color, int guiWidth,
                  Point startPoint, int frameThickness, int deltaX, int width) {
        this.shape = new Rectangle(startPoint, width, DEFAULT_HEIGHT);
        this.color = color;
        this.keyboard = newKeyboard;
        this.gameFrameThickness = frameThickness;
        this.gameGuiWidth = guiWidth;
        this.deltaX = deltaX;
    }

    /**
     * Move the paddle one step left.
     */
    public void moveLeft() {
        Point upperLeft = this.shape.getUpperLeft();
        double newX = Math.max(upperLeft.getX() - this.deltaX, gameFrameThickness);
        Point newLocation = new Point(newX, upperLeft.getY());
        this.shape = new Rectangle(newLocation, this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * Move the paddle one step right.
     */
    public void moveRight() {
        double limit = gameGuiWidth - gameFrameThickness;
        Point upperRight = this.shape.getRightSide().start();
        Point upperLeft = this.shape.getUpperLeft();
        Point newLocation;
        if (upperRight.getX() + this.deltaX > limit) {
            newLocation = new Point(limit - this.shape.getWidth(), upperLeft.getY());
        } else {
            newLocation = new Point(upperLeft.getX() + this.deltaX, upperLeft.getY());
        }
        this.shape = new Rectangle(newLocation, this.shape.getWidth(), this.shape.getHeight());
    }

    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line leftSide = this.shape.getLeftSide();
        Line rightSide = this.shape.getRightSide();
        Velocity newVelocity;
        // if the collision point is on the left/right side of the block - change the horizontal direction.
        if (leftSide.isInSegment(collisionPoint) || rightSide.isInSegment(collisionPoint)) {
            newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            // The collision point is on the upper side of the block.
            newVelocity = velocityByRegion(collisionPoint, currentVelocity);
        }
        return newVelocity;
    }

    /**
     * Return the new velocity of the ball after hitting the upper side of the paddle.
     *
     * @param collisionPoint the collision point of the ball and the block
     * @param currentVelocity the velocity of the ball
     * @return the new velocity expected after the hit
     */
    private Velocity velocityByRegion(Point collisionPoint, Velocity currentVelocity) {
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        Point paddleUpperLeft = this.shape.getUpperLeft();
        double paddleUpperLeftY = paddleUpperLeft.getY();
        double regionWidth = this.shape.getWidth() / REGIONS_NUM;
        Velocity newVelocity = currentVelocity; // default
        // Iterate through the regions and check where is the collision point. set the new velocity accordingly.
        for (int i = 0; i < REGIONS_NUM; i++) {
            Point regionStart = new Point(paddleUpperLeft.getX() + regionWidth * i, paddleUpperLeftY);
            Point regionEnd = new Point(regionStart.getX() + regionWidth, paddleUpperLeftY);
            Line currentRegion = new Line(regionStart, regionEnd);
            int angle = (LEFT_ANGLE + (DELTA_ANGLE * i)) % 360;
            if (currentRegion.isInSegment(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(angle, speed);
                break;
            }
        }
        return newVelocity;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
