package levels;

import general.HelpingMethods;
import general.Velocity;
import sprites.Block;
import sprites.Paddle;
import sprites.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * The level "Colorful".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class ColorfulLevel extends BasicLevel {
    static final int BALL_SPEED = 4;
    static final int BLOCK_WIDTH = 50;
    static final int BLOCK_HEIGHT = 20;
    // The number of blocks in the upper line of blocks.
    static final int FIRST_ROW_N_BLOCKS = 12;
    // The Y value of the upper left corner, of the blocks on the upper row.
    static final double FIRST_ROW_Y = 200;

    /**
     * The constructor of the "Colorful" level.
     */
    public ColorfulLevel() {
        super(2, 4, Paddle.DEFAULT_WIDTH, "Colorful", HelpingMethods.CREAM,
                Paddle.DEFAULT_START_POINT, HelpingMethods.BROWN);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(160 + i, BALL_SPEED));
        }
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new BasicLevelBackground(this.backgroundColor(), this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.addAll(GameLevel.createBlocksRow(FIRST_ROW_N_BLOCKS, FIRST_ROW_Y,
                HelpingMethods.BLUE, BLOCK_WIDTH, BLOCK_HEIGHT));
        blocks.addAll(GameLevel.createBlocksRow(FIRST_ROW_N_BLOCKS - 1, FIRST_ROW_Y + BLOCK_HEIGHT,
                HelpingMethods.LIGHT_BLUE, BLOCK_WIDTH, BLOCK_HEIGHT));
        blocks.addAll(GameLevel.createBlocksRow(FIRST_ROW_N_BLOCKS - 2, FIRST_ROW_Y + 2 * BLOCK_HEIGHT,
                HelpingMethods.GREEN, BLOCK_WIDTH, BLOCK_HEIGHT));
        blocks.addAll(GameLevel.createBlocksRow(FIRST_ROW_N_BLOCKS - 3, FIRST_ROW_Y + 3 * BLOCK_HEIGHT,
                HelpingMethods.PURPLE, BLOCK_WIDTH, BLOCK_HEIGHT));
        blocks.addAll(GameLevel.createBlocksRow(FIRST_ROW_N_BLOCKS - 4, FIRST_ROW_Y + 4 * BLOCK_HEIGHT,
                HelpingMethods.PINK, BLOCK_WIDTH, BLOCK_HEIGHT));
        blocks.addAll(GameLevel.createBlocksRow(FIRST_ROW_N_BLOCKS - 5, FIRST_ROW_Y + 5 * BLOCK_HEIGHT,
                HelpingMethods.ORANGE, BLOCK_WIDTH, BLOCK_HEIGHT));
        return blocks;
    }
}
