package levels;

import biuoop.DrawSurface;
import general.HelpingMethods;
import java.awt.Color;

/**
 * The background of a level "LegoMan".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class LegoManLevelBackground extends BasicLevelBackground {
    // face top and bottom
    static final int TOP_AND_BOTTOM_HEIGHT = 30;
    static final int TOP_AND_BOTTOM_WIDTH = 300;
    static final int EYE_SIZE = 40;
    static final int SMILE_SIDE_SIZE = 30;

    /**
     * The constructor of "LegoManLevelBackground".
     *
     * @param backgroundColor The color of the background.
     * @param levelName The name of the level.
     */
    public LegoManLevelBackground(Color backgroundColor, String levelName) {
        super(backgroundColor, levelName);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        //face
        d.setColor(HelpingMethods.YELLOW);
        d.fillRectangle(200, 250, 400, 250);
        d.fillRectangle(250, 220, TOP_AND_BOTTOM_WIDTH, TOP_AND_BOTTOM_HEIGHT);
        d.fillRectangle(250, 500, TOP_AND_BOTTOM_WIDTH, TOP_AND_BOTTOM_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(200, 250, 400, 250);
        d.drawRectangle(250, 220, TOP_AND_BOTTOM_WIDTH, TOP_AND_BOTTOM_HEIGHT);
        d.drawRectangle(250, 500, TOP_AND_BOTTOM_WIDTH, TOP_AND_BOTTOM_HEIGHT);
        // eyes
        d.fillRectangle(310, 310, EYE_SIZE, EYE_SIZE);
        d.fillRectangle(460, 310, EYE_SIZE, EYE_SIZE);
        // mouth
        d.fillRectangle(340, 430, 130, 30);
        d.fillRectangle(460, 405, SMILE_SIDE_SIZE, SMILE_SIDE_SIZE);
        d.fillRectangle(320, 405, SMILE_SIDE_SIZE, SMILE_SIDE_SIZE);
    }
}
