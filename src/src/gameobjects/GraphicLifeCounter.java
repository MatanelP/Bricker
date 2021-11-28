package src.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * Display a graphic object on the game window showing as many widgets as lives left.
 */
public class GraphicLifeCounter extends GameObject {
    private final Counter livesCounter;
    private final GameObjectCollection gameObjectsCollection;
    public static final int HEARTS_SPACING = 5;
    private final GameObject[] heartsArray;


    public GraphicLifeCounter(Vector2 widgetTopLeftCorner,
                              Vector2 widgetDimensions,
                              Counter livesCounter,
                              Renderable widgetRenderable,
                              GameObjectCollection gameObjectsCollection,
                              int numOfLives) {
        super(widgetTopLeftCorner, widgetDimensions, null);

        this.livesCounter = livesCounter;
        this.gameObjectsCollection = gameObjectsCollection;
        heartsArray = new GameObject[numOfLives + 1];

        // creating and adding the lives to the game.
        for (int i = 0; i < numOfLives; i++) {
            GameObject heart = new GameObject(
                    widgetTopLeftCorner.add(Vector2.RIGHT.mult(i * (widgetDimensions.x() + HEARTS_SPACING))),
                    widgetDimensions,
                    widgetRenderable);
            gameObjectsCollection.addGameObject(heart, Layer.BACKGROUND);
            heartsArray[i] = heart;
        }
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
        gameObjectsCollection.removeGameObject(heartsArray[livesCounter.value()], Layer.BACKGROUND);
    }
}
