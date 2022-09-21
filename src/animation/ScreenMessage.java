package animation;

import biuoop.DrawSurface;

/**
 * A message that appears on the screen.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class ScreenMessage implements Animation {
    static final int MESSAGE_LOCATION_X = 20;
    static final int FONT_SIZE = 32;

    private String message;

    /**
     * The constructor of "ScreenMessage".
     *
     * @param message The message to present on the screen.
     */
    public ScreenMessage(String message) {
        this.message = message;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(MESSAGE_LOCATION_X, d.getHeight() / 2, message, FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
