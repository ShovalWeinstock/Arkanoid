package sprites;

import biuoop.DrawSurface;
import levels.GameLevel;

/**
 * The interface "Sprite" -  an object that can be drawn to the screen
 * (and which is not just a background image).
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public interface Sprite {

    /**
     * Draw the sprite on the given surface.
     *
     * @param d The given surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add the sprite to the given game.
     *
     * @param g The given game.
     */
    void addToGame(GameLevel g);
}
