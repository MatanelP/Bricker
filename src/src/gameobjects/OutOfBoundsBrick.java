package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

/**
 * An object of this class deletes every other object that collides with it.
 * There for after placing it under the window displayed to player, it deletes out of bound object
 * like pucks and statuses.
 */
public class OutOfBoundsBrick extends Brick {
    public static final int BRICK_WIDTH = 50;
    private final GameObjectCollection objectCollection;

    /**
     * Construct a new GameObject instance.
     *
     * @param windowDimensions window dimensions to bound.
     * @param objectCollection object manager to delete OOB objects.
     */
    public OutOfBoundsBrick(Vector2 windowDimensions, GameObjectCollection objectCollection) {
        super(Vector2.DOWN.mult(windowDimensions.y()), new Vector2(windowDimensions.x(), (float) BRICK_WIDTH),
                null, null, null);
        this.objectCollection = objectCollection;
    }

    /**
     * Called on the first frame of a collision.
     *
     * @param other     The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        objectCollection.removeGameObject(other);
    }
}
