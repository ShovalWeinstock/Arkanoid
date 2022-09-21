package animation;

import biuoop.DrawSurface;

/**
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public interface Animation {

    /**
     * Perform a single frame on te given surface.
     *
     * @param d the given surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return whether the animation should stop.
     */
    boolean shouldStop();
}
