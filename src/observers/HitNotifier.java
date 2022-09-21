package observers;

/**
 * A notifier to a hit event.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public interface HitNotifier {
    /**
     * Add the given HitListener, "hl", as a listener to hit events.
     * @param hl The given listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove the given HitListener, "hl", from the list of listeners to hit events.
     * @param hl The given listener.
     */
    void removeHitListener(HitListener hl);
}
