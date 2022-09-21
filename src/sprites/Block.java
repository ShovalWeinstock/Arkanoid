package sprites;

import biuoop.DrawSurface;
import gamelogic.Collidable;
import gamelogic.CollisionInfo;
import levels.GameLevel;
import general.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import observers.HitListener;
import observers.HitNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * The object "Block".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // The shape of the block.
    private Rectangle shape;
    // The color of the block.
    private java.awt.Color color;
    // Info about a collision of the block.
    private CollisionInfo collision;
    // A list of the listeners of the block.
    private List<HitListener> hitListeners;

    /**
     * instructor of "Block".
     * @param shape The shape (rectangle) of the block.
     * @param color The color of the block.
     */
    public Block(Rectangle shape, java.awt.Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.collision = new CollisionInfo(collisionPoint, this);
        Line upperSide = this.shape.getUpperSide();
        Line bottomSide = this.shape.getBottomSide();
        Line leftSide = this.shape.getLeftSide();
        Line rightSide = this.shape.getRightSide();
        double newVelocityDX = currentVelocity.getDx();
        double newVelocityDY = currentVelocity.getDy();
        // if the collision point is on the left/right side of the block - change the horizontal direction.
        if (leftSide.isInSegment(collisionPoint) || rightSide.isInSegment(collisionPoint)) {
            newVelocityDX = -newVelocityDX;
        }
        // if the collision point is on the upper/bottom side of the block - change the vertical direction.
        if (upperSide.isInSegment(collisionPoint) || bottomSide.isInSegment(collisionPoint)) {
            newVelocityDY = -newVelocityDY;
        }
        this.notifyHit(hitter);
        return new Velocity(newVelocityDX, newVelocityDY);

    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

   @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Rempve the ball from the given game.
     *
     * @param game The given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all the listeners that the block was hit.
     *
     * @param hitter The ball that's doing the hitting.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Get the rectangle shape of the block.
     *
     * @return The shape of the block.
     */
    public Rectangle getShape() {
        return shape;
    }
}
