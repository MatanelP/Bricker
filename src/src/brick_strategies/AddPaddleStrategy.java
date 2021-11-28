package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.util.Vector2;

import src.gameobjects.MockPaddle;

/**
 * Concrete class extending abstract RemoveBrickStrategyDecorator.
 * Introduces extra paddle to game window which remains until colliding
 * NUM_COLLISION_TO_DISAPPEAR with other game objects.
 */
public class AddPaddleStrategy extends RemoveBrickStrategyDecorator implements CollisionStrategy {
    public static final int MOCK_PADDLE_WIDTH = 100;
    private static final int MOCK_PADDLE_HEIGHT = 15;
    private static final int MOCK_PADDLE_MIN_DIST_FROM_EDGE = 20;
    public static final String MOCK_PADDLE_IMG_PATH = "assets/paddle.png";
    private static final int NUM_COLLISION_TO_DISAPPEAR = 3;
    private final ImageReader imageReader;
    private final GameObjectCollection game_objects;
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;

    /**
     * Constructor.
     *
     * @param toBeDecorated    CollisionStrategy to be decorated.
     * @param imageReader      To read the mockPaddle image.
     * @param inputListener    To get user input for the mockPaddle.
     * @param windowDimensions Dimensions of the current windows.
     */
    public AddPaddleStrategy(CollisionStrategy toBeDecorated,
                             ImageReader imageReader,
                             UserInputListener inputListener,
                             Vector2 windowDimensions) {
        super(toBeDecorated);
        this.game_objects = toBeDecorated.getGameObjectCollection();
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    /**
     * Adds additional paddle to game and delegates to held object.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        toBeDecorated.onCollision(thisObj, otherObj, counter);
        addMockPaddle();
    }

    /*
        Creates and adds the mockPaddle gameObject.
     */
    private void addMockPaddle() {
        new MockPaddle(new Vector2(windowDimensions.x() / 2,
                windowDimensions.y() / 2),
                new Vector2(MOCK_PADDLE_WIDTH, MOCK_PADDLE_HEIGHT),
                imageReader.readImage(MOCK_PADDLE_IMG_PATH, true),
                inputListener,
                new Vector2(windowDimensions.x(), windowDimensions.y() / 2),
                game_objects, MOCK_PADDLE_MIN_DIST_FROM_EDGE,
                NUM_COLLISION_TO_DISAPPEAR);
    }
}

