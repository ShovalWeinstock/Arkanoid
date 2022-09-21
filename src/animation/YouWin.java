package animation;

/**
 * A "You win" screen.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class YouWin extends ScreenMessage {

    static final String WIN_MESSAGE = "You Win! Your score is ";

    /**
     * The constructor of "YouWin".
     *
     * @param score The score of the game.
     */
    public YouWin(int score) {
        super(WIN_MESSAGE + String.valueOf(score));
    }
}
