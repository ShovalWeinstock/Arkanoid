package levels;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The background of a level "DirectHit".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class DirectHitBackground extends BasicLevelBackground {
    static final int CIRCLES_CENTER_X = 400;
    static final int CIRCLES_CENTER_Y = 162;
    static final int LINE_SIZE = 140;

    /**
     * The constructor of "DirectHitBackground".
     *
     * @param backgroundColor The color of the background.
     * @param levelName The name of the level.
     */
    public DirectHitBackground(Color backgroundColor, String levelName) {
        super(backgroundColor, levelName);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        // The target
        d.setColor(Color.BLUE);
        d.drawCircle(CIRCLES_CENTER_X, CIRCLES_CENTER_Y, 60);
        d.drawCircle(CIRCLES_CENTER_X, CIRCLES_CENTER_Y, 90);
        d.drawCircle(CIRCLES_CENTER_X, CIRCLES_CENTER_Y, 120);
        d.drawLine(CIRCLES_CENTER_X, CIRCLES_CENTER_Y + 20, CIRCLES_CENTER_X, CIRCLES_CENTER_Y + LINE_SIZE);
        d.drawLine(CIRCLES_CENTER_X + 20, CIRCLES_CENTER_Y, CIRCLES_CENTER_X + LINE_SIZE, CIRCLES_CENTER_Y);
        d.drawLine(CIRCLES_CENTER_X - 20, CIRCLES_CENTER_Y, CIRCLES_CENTER_X - LINE_SIZE, CIRCLES_CENTER_Y);
        d.drawLine(CIRCLES_CENTER_X, CIRCLES_CENTER_Y - 20, CIRCLES_CENTER_X, CIRCLES_CENTER_Y - LINE_SIZE + 2);
    }
}
