package src;

/* IMPORTS */

import src.brick_strategies.BrickStrategyFactory;
import src.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.Color;
import java.util.Random;

/**
 * This class is responsible for game initialization,
 * holding references for game objects and calling update methods for
 * every update iteration.
 */
public class BrickerGameManager extends GameManager {

    /* FIELDS */

    // boarders:
    public static final int BORDER_WIDTH = 20; // default: 20
    // ball:
    private Ball ball;
    private static final int BALL_SPEED = 300;
    private static final int BALL_RADIUS = 20;
    private static final String BALL_IMG_PATH = "assets/ball.png";
    // paddle:
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 15;
    private static final int PADDLE_DIST_FROM_BOTTOM = 20;
    private static final String PADDLE_IMG_PATH = "assets/paddle.png";
    // bricks:
    private Counter brick_counter;
    private static final int NUM_OF_BRICKS_ROWS = 5; // default: 5
    private static final int NUM_OF_BRICKS_IN_ROM = 8; // default: 8
    private static final int BRICKS_SPACING = 8;
    private static final int BRICK_HEIGHT = 15;
    // lives:
    private Counter life_counter;
    private static final int NUM_OF_LIVES = 4; // default: 4
    private static final int LIFE_SIZE = 17;
    private static final int LIVES_SPACING = 5;
    private static final int NUMERIC_LIFE_SPACE_RIGHT = 7;
    public static final String LIFE_IMG_PATH = "assets/heart.png";
    // game:
    private static final String BLOP_SOUND_PATH = "assets/blop_cut_silenced.wav";
    private static final String WINDOW_TITLE = "Bricker";
    private static final String BACKGROUND_IMG_PATH = "assets/DARK_BG2_small.jpeg";
    private static final String WIN_MSG = "You Win! Play again?";
    private static final String LOSE_MSG = "You Lose! Play again?";
    private Vector2 windowDimensions;
    private ImageReader imageReader;
    private WindowController windowController;

    /**
     * CONSTRUCTOR
     *
     * @param windowTitle      - title for the window
     * @param windowDimensions - pixel dimensions for game window height x width.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    /* PUBLIC METHODS */

    /**
     * This function initializes the game window.
     * It initializes objects in the game window -
     * ball, paddle, walls, life counters, bricks.
     * This version of the game has 5 rows, 8 columns of bricks.
     *
     * @param imageReader      - an ImageReader instance for reading images from files for rendering of objects.
     * @param soundReader      - a SoundReader instance for reading sound clips from files for rendering event sounds.
     * @param inputListener    - an InputListener instance for reading user input.
     * @param windowController - controls visual rendering of the game window and object renderables.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        windowController.setTargetFramerate(60);
        this.imageReader = imageReader;
        this.windowController = windowController;
        this.brick_counter = new Counter(0);
        this.life_counter = new Counter(NUM_OF_LIVES);
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.windowDimensions = windowController.getWindowDimensions();

        createBall(imageReader, soundReader);

        createPaddle(imageReader, inputListener, windowDimensions);

        createWall(Vector2.ZERO, new Vector2(BORDER_WIDTH, windowDimensions.y()));
        createWall(new Vector2(windowDimensions.x() - BORDER_WIDTH, 0),
                new Vector2(BORDER_WIDTH, windowDimensions.y()));
        createWall(Vector2.ZERO, new Vector2(windowDimensions.x(), (float) BORDER_WIDTH));

        createBackground(imageReader, windowController);

        BrickStrategyFactory factory = new BrickStrategyFactory(gameObjects(), this, imageReader,
                soundReader, inputListener, windowController, windowDimensions);
        createBricks(imageReader, windowDimensions, factory);

        OutOfBoundsBrick OOBBrick = new OutOfBoundsBrick(windowDimensions, gameObjects());
        gameObjects().addGameObject(OOBBrick);

        createLifeCounter();
    }

    /**
     * Called once per frame. Any logic is put here. Rendering, on the other hand,
     * should only be done within 'render'.
     * Note that the time that passes between subsequent calls to this method is not constant.
     *
     * @param deltaTime The time, in seconds, that passed since the last invocation
     *                  of this method (i.e., since the last frame). This is useful
     *                  for either accumulating the total time that passed since some
     *                  event, or for physics integration (i.e., multiply this by
     *                  the acceleration to get an estimate of the added velocity or
     *                  by the velocity to get an estimate of the difference in position).
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        double ballHeight = ball.getCenter().y();
        String prompt = "";
        if (brick_counter.value() <= 0)
            prompt = WIN_MSG;
        if (ballHeight > windowDimensions.y() - PADDLE_DIST_FROM_BOTTOM) { // player lost
            if (life_counter.value() > 1) {
                life_counter.decrement();
                repositionBall(ball);
            } else {
                prompt = LOSE_MSG;
            }
        }
        if (!prompt.isEmpty()) {
            if (windowController.openYesNoDialog(prompt)) {
                MockPaddle.isInstantiated = false;
                windowController.resetGame();
            } else
                windowController.closeWindow();
        }
    }

    /* PRIVATE METHODS */

    /*
        Creates the life (strikes) system. Both graphic and numeric.
     */
    private void createLifeCounter() {
        GraphicLifeCounter graphicLifeCounter = new GraphicLifeCounter(
                new Vector2(BORDER_WIDTH + LIVES_SPACING,
                        windowDimensions.y() - LIFE_SIZE - LIVES_SPACING),
                new Vector2(LIFE_SIZE, LIFE_SIZE),
                life_counter,
                imageReader.readImage(LIFE_IMG_PATH, true),
                gameObjects(),
                NUM_OF_LIVES);
        NumericLifeCounter numericLifeCounter = new NumericLifeCounter(
                life_counter,
                new Vector2(windowDimensions.x() - BORDER_WIDTH - LIVES_SPACING
                        - LIFE_SIZE * NUMERIC_LIFE_SPACE_RIGHT,
                        windowDimensions.y() - LIFE_SIZE - LIVES_SPACING),
                new Vector2(LIFE_SIZE, LIFE_SIZE),
                gameObjects());
        gameObjects().addGameObject(graphicLifeCounter, Layer.BACKGROUND);
        gameObjects().addGameObject(numericLifeCounter, Layer.BACKGROUND);
    }

    /*
        Resetting the ball in the center of the screen.
        Also resets it direction.
     */
    public void repositionBall(GameObject ball) {
        ball.setCenter(windowDimensions.mult(0.5f));
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean())
            ballVelX *= -1;
        if (rand.nextBoolean())
            ballVelY *= -1;
        ball.setVelocity(new Vector2(ballVelX, ballVelY));
    }

    /*
        creating bricks according to constants.
     */
    private void createBricks(ImageReader imageReader, Vector2 windowDimensions, BrickStrategyFactory factory) {
        float brick_width = (windowDimensions.x() - BORDER_WIDTH * 2) / NUM_OF_BRICKS_IN_ROM;
        for (int i = 0; i < NUM_OF_BRICKS_ROWS; i++) {
            for (int j = 0; j < NUM_OF_BRICKS_IN_ROM; j++) {
                GameObject brick = new Brick(new Vector2(BORDER_WIDTH + (j * brick_width),
                        BORDER_WIDTH + (i * (BRICK_HEIGHT + BRICKS_SPACING))),
                        new Vector2(brick_width, BRICK_HEIGHT),
                        imageReader.readImage("assets/brick.png", false),
                        factory.getStrategy(),
                        brick_counter);
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
                brick_counter.increment();

            }
        }
    }

    /*
        creating the background.
     */
    private void createBackground(ImageReader imageReader, WindowController windowController) {
        GameObject background = new GameObject(
                Vector2.ZERO,
                windowController.getWindowDimensions(),
                imageReader.readImage(BACKGROUND_IMG_PATH, false));
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /*
        creating the paddle according to constants.
     */
    private void createPaddle(ImageReader imageReader, UserInputListener inputListener,
                              Vector2 windowDimensions) {
        Renderable paddleImage = imageReader.readImage(PADDLE_IMG_PATH, true);
        GameObject paddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), paddleImage,
                inputListener, windowDimensions, BORDER_WIDTH);
        paddle.setCenter(new Vector2(windowDimensions.x() / 2,
                windowDimensions.y() - PADDLE_DIST_FROM_BOTTOM - PADDLE_HEIGHT));
        gameObjects().addGameObject(paddle);
    }

    /*
        creating the ball according to constants.
     */
    private void createBall(ImageReader imageReader, SoundReader soundReader) {
        Renderable ballImage = imageReader.readImage(BALL_IMG_PATH, true);
        ball = new Ball(Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS),
                ballImage, soundReader.readSound(BLOP_SOUND_PATH));
        repositionBall(ball);
        this.gameObjects().addGameObject(ball);
    }

    /*
        creating a wall.
     */
    private void createWall(Vector2 location, Vector2 size) {
        gameObjects().addGameObject(new GameObject(location, size, new RectangleRenderable(Color.BLUE)));
    }

    /* MAIN */

    public static void main(String[] args) {
        new BrickerGameManager(WINDOW_TITLE, new Vector2(700, 500)).run(); // default: 700*500
    }
}
