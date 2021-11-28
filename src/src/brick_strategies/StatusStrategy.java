package src.brick_strategies;

import danogl.GameObject;

/**
 * All strategies that implements a status.
 */
public interface StatusStrategy {

    /**
     * All strategies should have a method to activate the affect of the status.
     */
    void activateAffect(GameObject other);
}
