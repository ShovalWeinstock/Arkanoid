package levels;

import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.Paddle;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The level "DirectHit".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class DirectHitLevel extends BasicLevel {
    static final int BALL_SPEED = 4;

    /**
     * The constructor of the "DirectHit" level.
     */
    public DirectHitLevel() {
        super(1, 4, Paddle.DEFAULT_WIDTH, "Direct Hit", Color.BLACK,
                Paddle.DEFAULT_START_POINT, Color.YELLOW);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity ballVelocity = Velocity.fromAngleAndSpeed(0, BALL_SPEED);
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(ballVelocity);
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground(this.backgroundColor(), this.levelName());
    }

    @Override
    public List<Block> blocks() {
        Point blockLocation = new Point(390, 150);
        Block block = new Block(new Rectangle(blockLocation, 22, 22), Color.RED);
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBalls();
    }
}
