package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

/**
 * Concrete brick strategy implementing CollisionStrategy interface. Removes holding brick on collision.
 */
public class RemoveBrickStrategy implements CollisionStrategy {

    private final GameObjectCollection gameObjects;

    /**
     * Constructor.
     *
     * @param gameObjects global game object collection
     */
    public RemoveBrickStrategy(GameObjectCollection gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * All collision strategy objects should hold a reference to the global
     * game object collection and be able to return it.
     *
     * @return global game object collection whose reference is held in object.
     */
    public GameObjectCollection getGameObjectCollection() {
        return gameObjects;
    }

    /**
     * Removes brick from game object collection on collision.
     *
     * @param counter global brick counter.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        if (gameObjects.removeGameObject(thisObj, Layer.STATIC_OBJECTS))
            counter.decrement();
    }
}
