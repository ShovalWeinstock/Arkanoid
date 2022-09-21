package animation;

/**
 * A "Game Over" screen.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class GameOver extends ScreenMessage {

    static final String GAME_OVER_MESSAGE = "Game Over. Your score is ";

    /**
     * The constructor of "GameOver".
     *
     * @param score The score of the game.
     */
    public GameOver(int score) {
        super(GAME_OVER_MESSAGE + String.valueOf(score));
    }
}
