package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

/**
 * Display a graphic object on the game window showing a numeric count of lives left.
 */
public class NumericLifeCounter extends GameObject {
    private final Counter livesCounter;
    TextRenderable textRenderable;

    public NumericLifeCounter(Counter livesCounter,
                              Vector2 topLeftCorner,
                              Vector2 dimensions,
                              GameObjectCollection gameObjectCollection) {

        super(topLeftCorner, dimensions, null);
        this.livesCounter = livesCounter;
        textRenderable = new TextRenderable("", null, false, true); // bolded text
        textRenderable.setColor(Color.RED);
        GameObject text = new GameObject(topLeftCorner, dimensions, textRenderable);
        gameObjectCollection.addGameObject(text, Layer.BACKGROUND);
    }


    /**
     * Should be called once per frame.
     *
     * @param deltaTime The time elapsed, in seconds, since the last frame. Can
     *                  be used to determine a new position/velocity by multiplying
     *                  this delta with the velocity/acceleration respectively
     *                  and adding to the position/velocity:
     *                  velocity += deltaTime*acceleration
     *                  pos += deltaTime*velocity
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        textRenderable.setString("Lives Left: " + livesCounter.value());
    }
}
