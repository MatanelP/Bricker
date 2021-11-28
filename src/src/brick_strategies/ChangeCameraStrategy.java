package src.brick_strategies;

import danogl.GameObject;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.BrickerGameManager;
import src.gameobjects.Ball;
import src.gameobjects.BallCollisionCountdownAgent;
import src.gameobjects.Puck;

/**
 * Concrete class extending abstract RemoveBrickStrategyDecorator.
 * Changes camera focus from ground to ball until ball collides COUNT_DOWN_VALUE times.
 */
public class ChangeCameraStrategy extends RemoveBrickStrategyDecorator implements CollisionStrategy {
    public static final int COUNT_DOWN_VALUE = 4;
    private final CollisionStrategy toBeDecorated;
    private final WindowController windowController;
    private final BrickerGameManager gameManager;
    private BallCollisionCountdownAgent collisionCount = null;

    /**
     * Constructor
     *
     * @param toBeDecorated    CollisionStrategy to decorate.
     * @param windowController The controller for the window.
     * @param gameManager      The game to change the camera for.
     */
    public ChangeCameraStrategy(CollisionStrategy toBeDecorated,
                                WindowController windowController, BrickerGameManager gameManager) {
        super(toBeDecorated);
        this.toBeDecorated = toBeDecorated;
        this.windowController = windowController;
        this.gameManager = gameManager;
    }

    /**
     * Change camera position on collision and delegate to held CollisionStrategy.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        toBeDecorated.onCollision(thisObj, otherObj, counter);
        if (otherObj instanceof Puck)
            return;
        if (gameManager.getCamera() == null) {
            collisionCount = new BallCollisionCountdownAgent((Ball) otherObj, this, COUNT_DOWN_VALUE);
            getGameObjectCollection().addGameObject(collisionCount);
            gameManager.setCamera(new Camera(otherObj, Vector2.ZERO,
                    windowController.getWindowDimensions().mult(1.2f),
                    windowController.getWindowDimensions()));
        }
    }

    /**
     * Return camera to normal ground position.
     */
    public void turnOffCameraChange() {
        gameManager.setCamera(null);
        getGameObjectCollection().removeGameObject(collisionCount);
        collisionCount = null;
    }
}
