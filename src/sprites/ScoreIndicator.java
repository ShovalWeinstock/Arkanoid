package sprites;

import biuoop.DrawSurface;
import levels.GameLevel;
import general.Counter;

import java.awt.Color;

/**
 * A counter of score.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public class ScoreIndicator implements Sprite {
    static final int TOP_LEFT_X = 390;
    static final int TOP_LEFT_Y = 20;
    static final int FONT_SIZE = 15;

    private Counter score;

    /**
     * The constructor of "Score counter".
     *
     * @param scoreCounter A counter of the score.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    /**
     * Draw the sprite on the given surface.
     *
     * @param d The given surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(TOP_LEFT_X, TOP_LEFT_Y, "Score: " + score.getValue(), FONT_SIZE);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
