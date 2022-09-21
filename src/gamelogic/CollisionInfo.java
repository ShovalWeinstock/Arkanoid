package gamelogic;

import geometry.Point;

/**
 * The object "CollisionInfo" .
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instructor of "Collision info".
     *
     * @param collisionPoint The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Get the point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Get the collidable object involved in the collision.
     *
     * @return The collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
