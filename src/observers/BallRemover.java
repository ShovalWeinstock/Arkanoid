package observers;

import levels.GameLevel;
import general.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * Remover of balls from a game.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public class BallRemover implements HitListener {
    // The game of the ball remover.
    private GameLevel game;
    // The number of remaining balls in the game.
    private Counter remainingBalls;

    /**
     * The constructor of "Ball Remove".
     *
     * @param game The game of the ball remover.
     * @param remainingBalls The number of remaining balls in the given game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
