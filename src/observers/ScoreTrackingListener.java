package observers;

import general.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * A listener for score tracking.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * The constructor of "ScoreTrackingListener".
     *
     * @param scoreCounter The counter of score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
