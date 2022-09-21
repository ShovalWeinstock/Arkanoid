package observers;

import levels.GameLevel;
import general.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * Removes blocks from the game.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public class BlockRemover implements HitListener {
    // The game of the block remover.
    private GameLevel game;
    // The number of remaining blocks on the game.
    private Counter remainingBlocks;

    /**
     * The constructor of "Block remover".
     *
     * @param game The game of the block remover.
     * @param removedBlocks The number of remaining blocks on the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

   @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the listener from the block that was hit
        beingHit.removeHitListener(this);
        // Decrease the number of remaining balls in the game.
        remainingBlocks.decrease(1);
        // Remove the block from the game.
        beingHit.removeFromGame(this.game);
    }
}
