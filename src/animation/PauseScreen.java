package animation;

/**
 * A "pause" screen.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class PauseScreen extends ScreenMessage {
    static final String PAUSE_MESSAGE = "paused -- press space to continue";

    /**
     * The constructor of "PauseScreen".
     */
    public PauseScreen() {
        super(PAUSE_MESSAGE);
    }
}