package levels;

import biuoop.DrawSurface;
import general.HelpingMethods;
import java.awt.Color;

/**
 * The background of a level "CandyLand".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class CandyLandLevelBackground extends BasicLevelBackground {
    static final int STICK_HEIGHT = 160;
    static final int STICK_WIDTH = 10;
    static final int CANDY_RADIUS = 45;
    static final int CANDY_STRIPE_HEIGHT = 10;
    static final int STICK_STRIPE_WIDTH = 100;
    static final int CANDY_CENTER_Y = 400;
    static final int CANDY_STRIPE_Y = 395;



    /**
     * The constructor of "CandyLandLevelBackground".
     *
     * @param backgroundColor The color of the background.
     * @param levelName The name of the level.
     */
    public CandyLandLevelBackground(Color backgroundColor, String levelName) {
        super(backgroundColor, levelName);
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the base of the background.
        super.drawOn(d);

        // Add the candies:
        // sticks
        d.setColor(Color.WHITE);
        d.fillRectangle(145, CANDY_CENTER_Y, STICK_WIDTH, STICK_HEIGHT);
        d.fillRectangle(400, CANDY_CENTER_Y, STICK_WIDTH, STICK_HEIGHT);
        d.fillRectangle(655, CANDY_CENTER_Y, STICK_WIDTH, STICK_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(145, CANDY_CENTER_Y, STICK_WIDTH, STICK_HEIGHT);
        d.drawRectangle(400, CANDY_CENTER_Y, STICK_WIDTH, STICK_HEIGHT);
        d.drawRectangle(655, CANDY_CENTER_Y, STICK_WIDTH, STICK_HEIGHT);

        //candies circles
        d.setColor(Color.RED);
        d.fillCircle(150, CANDY_CENTER_Y, CANDY_RADIUS);
        d.setColor(Color.ORANGE);
        d.fillCircle(660, CANDY_CENTER_Y, CANDY_RADIUS);
        d.setColor(HelpingMethods.LIGHT_GREEN);
        d.fillCircle(405, CANDY_CENTER_Y, CANDY_RADIUS);
        d.setColor(Color.BLACK);
        d.drawCircle(150, CANDY_CENTER_Y, CANDY_RADIUS);
        d.drawCircle(660, CANDY_CENTER_Y, CANDY_RADIUS);
        d.drawCircle(405, CANDY_CENTER_Y, CANDY_RADIUS);

        //candies stripes
        d.setColor(Color.RED);
        d.fillRectangle(100, CANDY_STRIPE_Y, STICK_STRIPE_WIDTH, CANDY_STRIPE_HEIGHT);
        d.setColor(Color.ORANGE);
        d.fillRectangle(610, CANDY_STRIPE_Y, STICK_STRIPE_WIDTH, CANDY_STRIPE_HEIGHT);
        d.setColor(HelpingMethods.LIGHT_GREEN);
        d.fillRectangle(355, CANDY_STRIPE_Y, STICK_STRIPE_WIDTH, CANDY_STRIPE_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(100, CANDY_STRIPE_Y, STICK_STRIPE_WIDTH, CANDY_STRIPE_HEIGHT);
        d.drawRectangle(610, CANDY_STRIPE_Y, STICK_STRIPE_WIDTH, CANDY_STRIPE_HEIGHT);
        d.drawRectangle(355, CANDY_STRIPE_Y, STICK_STRIPE_WIDTH, CANDY_STRIPE_HEIGHT);
    }
}
