package sprites;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Creating and performing operations on a collection of "sprites".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class SpriteCollection {
    // The collection of sprites
    private java.util.List<Sprite> sprites;

    /**
     * Constructor of "SpriteCollection".
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add the given "sprite" to the collection.
     *
     * @param s the given "sprite".
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove the given sprite form the collection.
     *
     * @param s The goven sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Call timePassed() on all sprites (notify them that time passed).
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spritesList) {
            sprite.timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites (draw them on the given surface).
     *
     * @param d The given surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
