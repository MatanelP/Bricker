package src.brick_strategies;

import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import src.BrickerGameManager;

import java.util.Random;

/**
 * Factory class for creating Collision strategies.
 */
public class BrickStrategyFactory {
    private final GameObjectCollection gameObjectCollection;
    private final BrickerGameManager gameManager;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener inputListener;
    private final WindowController windowController;
    private final Vector2 windowDimensions;

    /**
     * @param gameObjectCollection Game object manager.
     * @param gameManager          The game to manage.
     * @param imageReader          To load images for the strategy.
     * @param soundReader          To load sounds for the strategy.
     * @param inputListener        To get user input of the strategy
     * @param windowController     The controller for the window.
     * @param windowDimensions     The dimensions of the current window.
     */
    public BrickStrategyFactory(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager,
                                ImageReader imageReader, SoundReader soundReader,
                                UserInputListener inputListener, WindowController windowController,
                                Vector2 windowDimensions) {
        this.gameObjectCollection = gameObjectCollection;
        this.gameManager = gameManager;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.windowController = windowController;
        this.windowDimensions = windowDimensions;
    }

    /**
     * method randomly selects between 5 strategies and returns one CollisionStrategy object which is a
     * RemoveBrickStrategy decorated by one of the decorator strategies, or decorated by two randomly
     * selected strategies, or decorated by one of the decorator strategies
     * and a pair of additional two decorator strategies.
     *
     * @return CollisionStrategy
     */
    public CollisionStrategy getStrategy() {
        CollisionStrategy basicStrategy = new RemoveBrickStrategy(gameObjectCollection);
        switch (Strategies.values()[new Random().nextInt(Strategies.values().length)]) {
            case BASIC:
                return basicStrategy;
            case PUCK:
                return getPuckStrategy(basicStrategy);
            case PADDLE:
                return getAddPaddleStrategy(basicStrategy);
            case CAMERA:
                return getChangeCameraStrategy(basicStrategy);
            case SIZE:
                return getWideOrShrinkStrategy(basicStrategy);
            case DOUBLE:
                return DoubleBehavior(basicStrategy);
            default:
                return null;
        }
    }

    /* STRATEGIES CREATORS AND GETTERS */

    private CollisionStrategy DoubleBehavior(CollisionStrategy toBeDecorated) {
        if (new Random().nextInt(Strategies.values().length - 1) == 0) // triple behavior!
            return getDoubleBehaviorStrategy(getDoubleBehaviorStrategy(getDoubleBehaviorStrategy(toBeDecorated)));
        return getDoubleBehaviorStrategy(getDoubleBehaviorStrategy(toBeDecorated));
    }

    private CollisionStrategy getDoubleBehaviorStrategy(CollisionStrategy toBeDecorated) {
        switch (Strategies.values()[new Random().nextInt(Strategies.values().length - 2) + 1]) {
            case PUCK:
                return getPuckStrategy(toBeDecorated);
            case PADDLE:
                return getAddPaddleStrategy(toBeDecorated);
            case CAMERA:
                return getChangeCameraStrategy(toBeDecorated);
            case SIZE:
                return getWideOrShrinkStrategy(toBeDecorated);
            default:
                return null;
        }
    }

    private WideOrShrinkStrategy getWideOrShrinkStrategy(CollisionStrategy toBeDecorated) {
        return new WideOrShrinkStrategy(toBeDecorated,
                imageReader, windowDimensions);
    }

    private ChangeCameraStrategy getChangeCameraStrategy(CollisionStrategy toBeDecorated) {
        return new ChangeCameraStrategy(toBeDecorated,
                windowController, gameManager);
    }

    private AddPaddleStrategy getAddPaddleStrategy(CollisionStrategy toBeDecorated) {
        return new AddPaddleStrategy(toBeDecorated,
                imageReader, inputListener, windowDimensions);
    }

    private PuckStrategy getPuckStrategy(CollisionStrategy toBeDecorated) {
        return new PuckStrategy(toBeDecorated,
                imageReader, soundReader);
    }
}
