package levels;

import general.Velocity;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.List;

/**
 * The information of a level.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public interface LevelInformation {
    /**
     * Get the number of the balls of the level.
     *
     * @return the number of the balls of the level.
     */
    int numberOfBalls();

    /**
     * Get the initial velocity of each ball of the level.
     *
     * @return A list of the initial velocities of the balls of the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Get the initial location of each ball of the level.
     *
     * @return A list of the initial locations of the balls of the level.
     */
    List<Point> initialBallLocations();

    /**
     * Get the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Get the width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * Get the color of the paddle.
     *
     * @return the color of the paddle.
     */
    Color paddleColor();

    /**
     * Get the start point of the paddle.
     *
     * @return the start point of the paddle.
     */
    Point paddleStartPoint();

    /**
     * Get the name of the level.
     *
     * @return The name of the level.
     */
    String levelName();

    /**
     * Get the background of the level.
     *
     * @return the background of the level.
     */
    Sprite getBackground();

    /**
     * Get the background color of the level.
     *
     * @return the background color of the level.
     */
    Color backgroundColor();

    /**
     * Get the blocks of the level.
     *
     * @return A list of the blocks of the level.
     */
    List<Block> blocks();

    /**
     * Get the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
    }
