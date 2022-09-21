package levels;

import general.HelpingMethods;
import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.FramedBlock;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The level "LegoMan".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class LegoManLevel extends BasicLevel {
    static final int BLOCK_WIDTH = (HelpingMethods.GUI_WIDTH - 2 * (HelpingMethods.FRAME_THICKNESS)) / 15;
    static final int BLOCK_HEIGHT = 30;
    static final int BLOCKS_ROW_Y = 150;
    static final int BALL_SPEED = 4;

    /**
     * The constructor of the "LegoMan" level.
     */
    public LegoManLevel() {
        super(10, 4, 600, "Lego Man", HelpingMethods.LIGHT_BLUE,
                new Point(400 - 0.5 * 600, 545), HelpingMethods.BLUE);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity ballVelocity = Velocity.fromAngleAndSpeed(160 + 3 * i, BALL_SPEED);
            velocities.add(ballVelocity);
        }
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new LegoManLevelBackground(this.backgroundColor(), this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        int i = 0;
        while (i < 2) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.RED);
            blocks.add(b);
            i++;
        }
        while (i < 4) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.GREEN);
            blocks.add(b);
            i++;
        }
        while (i < 6) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.BLUE);
            blocks.add(b);
            i++;
        }
        while (i < 9) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.ORANGE);
            blocks.add(b);
            i++;
        }
        while (i < 11) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.PINK);
            blocks.add(b);
            i++;
        }
        while (i < 13) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.LIGHT_GRAY);
            blocks.add(b);
            i++;
        }
        while (i < 15) {
            Block b = new FramedBlock(new Rectangle(new Point(HelpingMethods.FRAME_THICKNESS + i * BLOCK_WIDTH,
                    BLOCKS_ROW_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.YELLOW);
            blocks.add(b);
            i++;
        }
        return blocks;
    }
}
