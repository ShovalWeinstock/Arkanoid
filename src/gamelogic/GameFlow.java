package gamelogic;

import animation.KeyPressStoppableAnimation;
import animation.Animation;
import animation.AnimationRunner;
import animation.GameOver;
import animation.YouWin;
import biuoop.KeyboardSensor;
import general.Counter;
import levels.GameLevel;
import levels.LevelInformation;
import java.util.List;

/**
 * The flow of a game.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * The constructor of "GameFlow".
     *
     * @param ar The animation runner of the game.
     * @param ks The keyboard sensor of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * running the levels of the game.
     *
     * @param levels the level of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        // if there are no levels, there is no endScreen for the game.
        Animation endScreen = null;
        for (LevelInformation levelInfo : levels) {
            // initialize each level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            // while there are blocks and balls left - run the game.
            while (level.leftBlocksNum() != 0 && level.leftBallsNum() != 0) {
                level.run();
            }
            // When there are no balls left - The user lost. End the game with a"GameOver" screen.
            if (level.leftBallsNum() == 0) {
                endScreen = new GameOver(this.score.getValue());
                break;
            }
            // The user won the game. End the game with a "YouWin" screen.
            endScreen = new YouWin(this.score.getValue());
        }
        // If there were no levels in the game there is no end screen. otherwise- present the end screen.
        if (endScreen != null) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                                    KeyboardSensor.SPACE_KEY, endScreen));
        }
        // Exit the game.
        this.animationRunner.getGui().close();
    }
}