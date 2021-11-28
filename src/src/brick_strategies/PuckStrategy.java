package src.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.util.Counter;
import danogl.util.Vector2;
import src.gameobjects.Puck;

import java.util.Random;

/**
 * Concrete class extending abstract RemoveBrickStrategyDecorator.
 * Introduces several pucks instead of brick once removed.
 */
public class PuckStrategy extends RemoveBrickStrategyDecorator implements CollisionStrategy {
    private static final int NUM_OF_NEW_PUCKS = 3;
    private static final float PUCK_SPEED = 300;
    private static final double[] directions = {1, 1.05, 1.1, 1.15, 1.2, -1, -1.05, -1.1, -1.15, -1.2};
    public static final String PUCK_IMG_PATH = "assets/mockBall.png";
    private static final String BLOP_SOUND_PATH = "assets/blop_cut_silenced.wav";
    private final GameObjectCollection game_objects;
    private final ImageReader imageReader;
    private final SoundReader soundReader;

    /**
     * Constructor.
     *
     * @param toBeDecorated CollisionStrategy to decorate.
     * @param imageReader   To read the image of the puck.
     * @param soundReader   To play the sound of collision.
     */
    public PuckStrategy(CollisionStrategy toBeDecorated,
                        ImageReader imageReader,
                        SoundReader soundReader) {
        super(toBeDecorated);
        this.game_objects = toBeDecorated.getGameObjectCollection();
        this.imageReader = imageReader;
        this.soundReader = soundReader;
    }


    /**
     * Add pucks to game on collision and delegate to held CollisionStrategy.
     *
     * @param counter global brick counter.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter counter) {
        toBeDecorated.onCollision(thisObj, otherObj, counter);
        addPucks(thisObj);
    }

    private void addPucks(GameObject brick) {
        for (int i = 0; i < NUM_OF_NEW_PUCKS; i++) {
            float puck_radius = brick.getDimensions().x() / 3;
            Puck puck = new Puck(brick.getCenter(), new Vector2(puck_radius, puck_radius),
                    imageReader.readImage(PUCK_IMG_PATH, true),
                    soundReader.readSound(BLOP_SOUND_PATH));
            float puck_vel_x = PUCK_SPEED;
            Random rand = new Random();
            puck_vel_x *= directions[rand.nextInt(directions.length)];
            puck.setVelocity(new Vector2(puck_vel_x, PUCK_SPEED));
            game_objects.addGameObject(puck);
        }
    }
}
