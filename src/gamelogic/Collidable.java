package gamelogic;

import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * The interface "Collidable".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * Returns the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint the collision point of the ball and the block.
     * @param currentVelocity the velocity of the ball.
     * @param hitter the ball that's doing the hitting.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
