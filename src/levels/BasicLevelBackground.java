package levels;

import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * The basic background of a level.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class BasicLevelBackground implements Sprite {
    // The X and Y value of the beginning of the level name.
    static final int TOP_LEFT_X = 580;
    static final int TOP_LEFT_Y = 20;
    static final int FONT_SIZE = 15;

    private Color backgroundColor;
    private String levelName;

    /**
     * The constructor of "BasicLevelBackground".
     *
     * @param backgroundColor The color of the background.
     * @param levelName The name of the level.
     */
    public BasicLevelBackground(Color backgroundColor, String levelName) {
        this.backgroundColor = backgroundColor;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(TOP_LEFT_X, TOP_LEFT_Y, "Level: " + this.levelName, FONT_SIZE);
        GameLevel.addBackground(d, this.backgroundColor);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
