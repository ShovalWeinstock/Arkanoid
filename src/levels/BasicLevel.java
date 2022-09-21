package levels;

import geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The basic information of a level.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public abstract class BasicLevel implements LevelInformation {
    static final int BALL_RADIUS = 5;

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Color backgroundColor;
    private Point paddleStartPoint;
    private Color paddleColor;
    private int numberOfBlocksToRemove;

    /**
     * The constructor of a basic level.
     *
     * @param numberOfBalls The number of balls in the level.
     * @param paddleSpeed The speed of the paddle.
     * @param paddleWidth The width of the paddle.
     * @param levelName The name of the level.
     * @param backgroundColor The background color of the level.
     * @param paddleStartPoint The start point of the paddle.
     * @param paddleColor The color of the paddle.
     */
    public BasicLevel(int numberOfBalls, int paddleSpeed, int paddleWidth, String levelName, Color backgroundColor,
                      Point paddleStartPoint, Color paddleColor) {
        this.numberOfBalls = numberOfBalls;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.backgroundColor = backgroundColor;
        this.paddleStartPoint = paddleStartPoint;
        this.paddleColor = paddleColor;
        this.numberOfBlocksToRemove = blocks().size();
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Color backgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public Color paddleColor() {
        return this.paddleColor;
    }

    @Override
    public Point paddleStartPoint() {
        return this.paddleStartPoint;
    }

    @Override
    public List<Point> initialBallLocations() {
        int ballsNum = this.numberOfBalls;
        List<Point> locations = new ArrayList<Point>();
        // The distance between two balls on the paddle.
        int distance = this.paddleWidth / this.numberOfBalls;
        // The x and y values of the location of the first ball on the paddle.
        double firstBallX = this.paddleStartPoint.getX() + (0.5 * distance);
        double firstBallY = this.paddleStartPoint.getY() - BALL_RADIUS;
        for (int i = 0; i < ballsNum; i++) {
            Point location = new Point(firstBallX + (distance * i), firstBallY);
            locations.add(location);
        }
        return locations;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
