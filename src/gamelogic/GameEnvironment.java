package gamelogic;

import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The object "GameEnvironment".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c the given collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove the given collidable from the game environment.
     * @param c The given collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Given a trajectory of an object- if this object will not collide with any of the collidables
     * in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory how the ball will move without any obstacles.
     * @return the information about the closest collision that is going to occur,
     * or null, if the object will not collide with any of the collidables.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // list of the objects that the ball would collide with.
        java.util.List<Collidable> collideWith = new ArrayList<Collidable>();
        // the collision points,respectively to the objects.
        java.util.List<Point> collisionPoints = new ArrayList<Point>();
        List<Collidable> collidablesList = new ArrayList<Collidable>(this.collidables);
        for (Collidable collidable : collidablesList) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (p != null) {
                collideWith.add(collidable);
                collisionPoints.add(p);
            }
        }
        int collideWithSize = collideWith.size();
        // if the ball doesn't collide with any object.
        if (collideWithSize == 0) {
            return null;
        }
        // set the first object in "collideWith" as the first object the ball collides with.
        Collidable firstCollidableObj = collideWith.get(0);
        Point firstCollisionPoint = collisionPoints.get(0);
        double smallestDistance = trajectory.start().distance(firstCollisionPoint);
        // if there are more objects the ball collides with - find the one closest to the start of the trajectory.
        if (collideWithSize != 1) {
            for (int j = 1; j < collideWithSize; j++) {
                double distance = trajectory.start().distance(collisionPoints.get(j));
                if (distance < smallestDistance) {
                    firstCollidableObj = collideWith.get(j);
                    firstCollisionPoint = collisionPoints.get(j);
                    smallestDistance = distance;
                }
            }
        }
        return new CollisionInfo(firstCollisionPoint, firstCollidableObj);
    }

    /**
     * Get the collidables collection.
     *
     * @return The collidables collection.
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }
}
