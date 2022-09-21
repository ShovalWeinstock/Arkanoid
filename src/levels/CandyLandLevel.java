package levels;

import general.HelpingMethods;
import general.Velocity;
import sprites.Block;
import sprites.Paddle;
import sprites.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * The level "CandyLand".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class CandyLandLevel extends BasicLevel {
    static final int BALL_SPEED = 4;
    static final int BLOCK_HEIGHT = 20;
    static final int FIRST_ROW_Y = 150;

    /**
     * The constructor of the "CandyLand" level.
     */
    public CandyLandLevel() {
        super(3, 4, Paddle.DEFAULT_WIDTH, "CandyLand", HelpingMethods.LIGHT_PINK,
                Paddle.DEFAULT_START_POINT, HelpingMethods.PINK);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int ballsNum = this.numberOfBalls();
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < ballsNum; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(160 + i, BALL_SPEED));
        }
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new CandyLandLevelBackground(this.backgroundColor(), this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < 7; i++) {
            blocks.addAll(GameLevel.createBlocksRow(15, FIRST_ROW_Y + i * BLOCK_HEIGHT,
                    HelpingMethods.BROWN, (HelpingMethods.GUI_WIDTH - (2 * HelpingMethods.FRAME_THICKNESS)) / 15,
                    BLOCK_HEIGHT));
        }
       return blocks;
    }
}
