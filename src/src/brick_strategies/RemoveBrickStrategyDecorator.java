package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

/**
 * Abstract decorator to add functionality to the remove brick strategy, following the decorator pattern.
 * All strategy decorators should inherit from this class.
 */
public abstract class RemoveBrickStrategyDecorator implements CollisionStrategy {

    protected CollisionStrategy toBeDecorated;

    /**
     * Constructor.
     *
     * @param toBeDecorated Collision strategy object to be decorated.
     */
    public RemoveBrickStrategyDecorator(CollisionStrategy toBeDecorated) {
        this.toBeDecorated = toBeDecorated;
    }

    /**
     * return held reference to global game object. Delegate to held object to be decorated
     */
    public GameObjectCollection getGameObjectCollection() {
        return toBeDecorated.getGameObjectCollection();
    }

    /**
     * Should delegate to held Collision strategy object.
     *
     * @param counter global brick counter.
     */
    @Override
    public abstract void onCollision(GameObject thisObj, GameObject otherObj, Counter counter);
}
