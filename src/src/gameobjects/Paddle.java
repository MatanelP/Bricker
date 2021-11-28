package src.gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;


/**
 * One of the main game objects. Repels the ball against the bricks.
 */
public class Paddle extends GameObject {

    private static final float MOVEMENT_SPEED = 600;
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;
    private final int minDistanceFromEdge;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     * @param inputListener The distance of the paddle from the edge wall barriers.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener inputListener, Vector2 windowDimensions, int minDistanceFromEdge) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
        this.minDistanceFromEdge = minDistanceFromEdge;
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
        Vector2 movementDir = Vector2.ZERO;
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);

        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }
        setVelocity(movementDir.mult(MOVEMENT_SPEED));

        if (getTopLeftCorner().x() < minDistanceFromEdge) {

            setCenter(new Vector2(minDistanceFromEdge + getDimensions().x() / 2,
                    getCenter().y()));
        }
        if (getTopLeftCorner().x() >
                windowDimensions.x() - minDistanceFromEdge - getDimensions().x()) {
            setCenter(new Vector2(windowDimensions.x() - minDistanceFromEdge - getDimensions().x() / 2,
                    getCenter().y()));

        }


    }
}
