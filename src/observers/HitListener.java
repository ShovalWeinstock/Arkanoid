package observers;

import sprites.Ball;
import sprites.Block;

/**
 * A listener to a hit event.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public interface HitListener {
    /**
     * This method is called whenever the "beingHit" object is hit.
     * It defines what happens when the hit happens.
     * @param beingHit The object that is being hit.
     * @param hitter The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
