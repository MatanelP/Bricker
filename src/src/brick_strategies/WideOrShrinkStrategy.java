package src.brick_strategies;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.Status;

import java.util.Random;

/**
 * This strategy will introduce a falling status.
 * If the status hits a paddle its affect will make the paddle narrower of wider with 50% chance for each.
 */
public class WideOrShrinkStrategy extends RemoveBrickStrategyDecorator implements CollisionStrategy,
        StatusStrategy {
    private static final float MIN_WIDTH = 20;
    private static final float STATUS_SPEED = 300;
    public static final String NARROW_IMG = "assets/buffNarrow.png";
    public static final String WIDEN_IMG = "assets/buffWiden.png";
    private final float max_width;
    private final CollisionStrategy toBeDecorated;
    private final ImageReader imageReader;
    private boolean isWidenAffect;

    /**
     * Constructor.
     *
     * @param toBeDecorated    CollisionStrategy to be decorated.
     * @param imageReader      To read the images for the strategy.
     * @param windowDimensions the dimension of the current window.
     */
    public WideOrShrinkStrategy(CollisionStrategy toBeDecorated, ImageReader imageReader,
                                Vector2 windowDimensions) {
        super(toBeDecorated);
        this.toBeDecorated = toBeDecorated;
        this.imageReader = imageReader;
        max_width = windowDimensions.x() * 0.75f;
    }

    /**
     * Dropping a wide or narrow status.
     *
     * @param counter global brick counter.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        toBeDecorated.onCollision(thisObj, otherObj, counter);
        Random random = new Random();
        Renderable renderable = imageReader.readImage(NARROW_IMG, true);
        isWidenAffect = false;
        if (random.nextBoolean()) {
            renderable = imageReader.readImage(WIDEN_IMG, true);
            isWidenAffect = true;
        }
        Status status = new Status(Vector2.ZERO, Vector2.ZERO, renderable,
                this, getGameObjectCollection());
        status.setCenter(thisObj.getCenter());
        status.setDimensions(thisObj.getDimensions());
        status.setVelocity(Vector2.DOWN.mult(STATUS_SPEED * 0.8f));
        getGameObjectCollection().addGameObject(status);
    }

    /**
     * Actively widening or narrowing the affected paddle.
     *
     * @param other The affected paddle.
     */
    public void activateAffect(GameObject other) {
        if (isWidenAffect) {
            if (other.getDimensions().x() * 1.5f < max_width)
                other.setDimensions(other.getDimensions().multX(1.5f));
        } else if (other.getDimensions().x() * 0.5f > MIN_WIDTH)
            other.setDimensions(other.getDimensions().multX(0.5f));

    }
}
