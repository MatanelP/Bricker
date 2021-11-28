// package test;

import src.BrickerGameManager;
import src.gameobjects.GraphicLifeCounter;
import src.gameobjects.NumericLifeCounter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class PreSubmit {
    private static final int NAME_INDEX = 0;
    private static final int RETURN_TYPE_INDEX = 1;
    private static final int PARAMETER_TYPES_INDEX = 2;
    public static final String[] CONSTANT_FIELDS = {};
    public static final String[][] GAMEMANAGER_PUBLIC_METHODS = {
            {"initializeGame", "void", "[class danogl.gui.ImageReader, class danogl.gui.SoundReader, interface danogl.gui.UserInputListener, interface danogl.gui.WindowController]"},
            {"update", "void", "[float]"},
    };
    public static final String[][] NUMCOUNTER_PUBLIC_METHODS = {
    };
    public static final String[][] GRAPHICCOUNTER_PUBLIC_METHODS ={
            {"update", "void", "[float]"}
    };
    public static final String[][] BALL_PUBLIC_METHODS ={
            {"onCollisionEnter", "void", "[class danogl.GameObject, interface danogl.collisions.Collision]"}
    };
    public static final String[][] BRICK_PUBLIC_METHODS ={
            {"onCollisionEnter", "void", "[class danogl.GameObject, interface danogl.collisions.Collision]"}
    };
    public static final String[][] PADDLE_PUBLIC_METHODS ={
            {"update", "void", "[float]"}
    };
    public static final String[][] MOCK_PADDLE_PUBLIC_METHODS ={
            {"onCollisionEnter", "void", "[class danogl.GameObject, interface danogl.collisions.Collision]"}
    };
    public static final String[][] PUCK_PUBLIC_METHODS = {};
    public static final String[][] BALL_COLLISION_COUNTDOWN_AGENT = {
            {"update", "void", "[float]"}
    };
    public static final String[][] COLLISION_STRATEGY_PUBLIC_METHODS = {
            {"onCollision", "void", "[class danogl.GameObject, class danogl.GameObject, class danogl.util.Counter]"},
            {"getGameObjectCollection", "danogl.collisions.GameObjectCollection", "[]"}
    };
    public static final String[][] REMOVE_BRICK_STRATEGY_DECORATOR_PUBLIC_METHODS = {
            {"onCollision", "void", "[class danogl.GameObject, class danogl.GameObject, class danogl.util.Counter]"},
            {"getGameObjectCollection", "danogl.collisions.GameObjectCollection", "[]"}
    };
    public static final String[][] REMOVE_BRICK_STRATEGY_PUBLIC_METHODS = {
            {"onCollision", "void", "[class danogl.GameObject, class danogl.GameObject, class danogl.util.Counter]"},
            {"getGameObjectCollection", "danogl.collisions.GameObjectCollection", "[]"}
    };
    public static final String[][] PUCK_STRATEGY_DECORATOR_PUBLIC_METHODS = {
            {"onCollision", "void", "[class danogl.GameObject, class danogl.GameObject, class danogl.util.Counter]"}
    };
    public static final String[][] CHANGE_CAMERA_STRATEGY_DECORATOR_PUBLIC_METHODS = {
            {"onCollision", "void", "[class danogl.GameObject, class danogl.GameObject, class danogl.util.Counter]"},
            {"turnOffCameraChange", "void", "[]"}
    };
    public static final String[][] ADD_PADDLE_STRATEGY_DECORATOR_PUBLIC_METHODS = {
            {"onCollision", "void", "[class danogl.GameObject, class danogl.GameObject, class danogl.util.Counter]"}
    };
    public static final String[][] BRICK_STRATEGY_FACTORY_DECORATOR_PUBLIC_METHODS = {
            {"getStrategy", "src.brick_strategies.CollisionStrategy", "[]"}
    };
    public static final String[][] ALGO_PUBLIC_METHODS = {
            {"alotStudyTime", "int", "[class [I, class [I]"},
            {"minLeap", "int", "[class [I]"}
    };




    public static final String[][] CONSTRUCTOR = {
            {"src.BrickerGameManager", "src.BrickerGameManager",
                    "[class java.lang.String, class danogl.util.Vector2]"
            },
            {"src.gameobjects.GraphicLifeCounter","src.gameobjects.GraphicLifeCounter",
                    "[class danogl.util.Vector2, class danogl.util.Vector2, class danogl.util.Counter, interface danogl.gui.rendering.Renderable, class danogl.collisions.GameObjectCollection, int]"
            }, //GraphicLifeCounter
            {"src.gameobjects.NumericLifeCounter","src.gameobjects.NumericLifeCounter",
                    "[class danogl.util.Counter, class danogl.util.Vector2, class danogl.util.Vector2, class danogl.collisions.GameObjectCollection]"
            }, //NumericLifeCounter
            {"src.gameobjects.Ball","src.gameobjects.Ball",
                    "[class danogl.util.Vector2, class danogl.util.Vector2, interface danogl.gui.rendering.Renderable, class danogl.gui.Sound]"
            }, //Ball
            {"src.gameobjects.Brick","src.gameobjects.Brick",
                    "[class danogl.util.Vector2, class danogl.util.Vector2, interface danogl.gui.rendering.Renderable, interface src.brick_strategies.CollisionStrategy, class danogl.util.Counter]"
            }, //Brick
            {"src.gameobjects.Paddle","src.gameobjects.Paddle",
                    "[class danogl.util.Vector2, class danogl.util.Vector2, interface danogl.gui.rendering.Renderable, interface danogl.gui.UserInputListener, class danogl.util.Vector2, int]"
            }, //Paddle
            {"src.gameobjects.MockPaddle","src.gameobjects.MockPaddle",
                    "[class danogl.util.Vector2, class danogl.util.Vector2, interface danogl.gui.rendering.Renderable, interface danogl.gui.UserInputListener, class danogl.util.Vector2, class danogl.collisions.GameObjectCollection, int, int]"
            }, //MockPaddle
            {"src.gameobjects.Puck","src.gameobjects.Puck",
                    "[class danogl.util.Vector2, class danogl.util.Vector2, interface danogl.gui.rendering.Renderable, class danogl.gui.Sound]"
            },//Puck
            {"src.gameobjects.BallCollisionCountdownAgent","src.gameobjects.BallCollisionCountdownAgent",
                    "[class src.gameobjects.Ball, class src.brick_strategies.ChangeCameraStrategy, int]"
            },//BallCollisionCountdownAgent
            {"src.brick_strategies.RemoveBrickStrategyDecorator","src.brick_strategies.RemoveBrickStrategyDecorator",
                    "[interface src.brick_strategies.CollisionStrategy]"
            },//RemoveBrickStrategyDecorator
            {"src.brick_strategies.RemoveBrickStrategy","src.brick_strategies.RemoveBrickStrategy",
                    "[class danogl.collisions.GameObjectCollection]"
            },//RemoveBrickStrategy
            {"src.brick_strategies.PuckStrategy","src.brick_strategies.PuckStrategy",
                    "[interface src.brick_strategies.CollisionStrategy, class danogl.gui.ImageReader, class danogl.gui.SoundReader]"
            }, // PuckStrategy
            {"src.brick_strategies.ChangeCameraStrategy","src.brick_strategies.ChangeCameraStrategy",
                    "[interface src.brick_strategies.CollisionStrategy, interface danogl.gui.WindowController, class src.BrickerGameManager]"
            },// ChangeCameraStrategy
            {"src.brick_strategies.BrickStrategyFactory","src.brick_strategies.BrickStrategyFactory",
                    "[class danogl.collisions.GameObjectCollection, class src.BrickerGameManager, class danogl.gui.ImageReader, class danogl.gui.SoundReader, interface danogl.gui.UserInputListener, interface danogl.gui.WindowController, class danogl.util.Vector2]"
            },// BrickStrategyFactory
            {"src.brick_strategies.AddPaddleStrategy","src.brick_strategies.AddPaddleStrategy",
                    "[interface src.brick_strategies.CollisionStrategy, class danogl.gui.ImageReader, interface danogl.gui.UserInputListener, class danogl.util.Vector2]"
            },// AddPaddleStrategy
    };

    public static final Map<String, String[][]> requiredMethods = new HashMap<String, String[][]>();

    public static void initRequiredMethods(){
        requiredMethods.put("src.BrickerGameManager", GAMEMANAGER_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.GraphicLifeCounter", GRAPHICCOUNTER_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.NumericLifeCounter", NUMCOUNTER_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.Ball", BALL_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.Brick", BRICK_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.Paddle", PADDLE_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.MockPaddle", MOCK_PADDLE_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.Puck", PUCK_PUBLIC_METHODS);
        requiredMethods.put("src.gameobjects.BallCollisionCountdownAgent", BALL_COLLISION_COUNTDOWN_AGENT);
        requiredMethods.put("src.brick_strategies.CollisionStrategy", COLLISION_STRATEGY_PUBLIC_METHODS);
        requiredMethods.put("src.brick_strategies.RemoveBrickStrategyDecorator", REMOVE_BRICK_STRATEGY_DECORATOR_PUBLIC_METHODS);
        requiredMethods.put("src.brick_strategies.RemoveBrickStrategy", REMOVE_BRICK_STRATEGY_PUBLIC_METHODS);
        requiredMethods.put("src.brick_strategies.PuckStrategy", PUCK_STRATEGY_DECORATOR_PUBLIC_METHODS);
        requiredMethods.put("src.brick_strategies.ChangeCameraStrategy", CHANGE_CAMERA_STRATEGY_DECORATOR_PUBLIC_METHODS);
        requiredMethods.put("src.brick_strategies.AddPaddleStrategy", ADD_PADDLE_STRATEGY_DECORATOR_PUBLIC_METHODS);
        requiredMethods.put("src.brick_strategies.BrickStrategyFactory", BRICK_STRATEGY_FACTORY_DECORATOR_PUBLIC_METHODS);

        //algo questions
        requiredMethods.put("algo_questions.Solutions", ALGO_PUBLIC_METHODS);
    }

    public static void main(String[] args) {
        initRequiredMethods();
        verifyFields();
        verifyExecutables();
    }

    private static void verifyFields() {
        boolean found;
        int mod;

        Field[] fields = BrickerGameManager.class.getDeclaredFields();

        for (String name : CONSTANT_FIELDS) {
            found = false;
            for (Field field : fields) {
                String fieldName = field.getName();
                if (fieldName.equals(name)) {
                    found = true;
                    mod = field.getModifiers();
                    if (!Modifier.isStatic(mod))
                        System.out.printf("%s_not_static%n", name);
                    if (!Modifier.isFinal(mod))
                        System.out.printf("%s_not_final%n", name);
                    if (Modifier.isPrivate(mod))
                        System.out.printf("%s_private%n", name);
                }
            }
            if (!found) {
                System.out.printf("%s_not_found%n", name);
            }
        }
    }

    private static void verifyExecutables() {
        boolean found;
        int mod;
        String returnType;
        String parameterTypes;

        Map<String,Constructor<?>[]> constructorsOfProjectClasses = new HashMap<String, Constructor<?>[]>();
        Map<String,Method[]> projectClassPublicMethods = new HashMap<String, Method[]>();

        constructorsOfProjectClasses.put("src.BrickerGameManager",BrickerGameManager.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.GraphicLifeCounter",src.gameobjects.GraphicLifeCounter.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.NumericLifeCounter",src.gameobjects.NumericLifeCounter.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.Ball",src.gameobjects.Ball.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.Brick",src.gameobjects.Brick.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.Paddle",src.gameobjects.Paddle.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.MockPaddle",src.gameobjects.MockPaddle.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.Puck",src.gameobjects.Puck.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.gameobjects.BallCollisionCountdownAgent",src.gameobjects.BallCollisionCountdownAgent.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.brick_strategies.RemoveBrickStrategyDecorator",src.brick_strategies.RemoveBrickStrategyDecorator.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.brick_strategies.RemoveBrickStrategy",src.brick_strategies.RemoveBrickStrategy.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.brick_strategies.PuckStrategy",src.brick_strategies.PuckStrategy.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.brick_strategies.ChangeCameraStrategy",src.brick_strategies.ChangeCameraStrategy.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.brick_strategies.BrickStrategyFactory",src.brick_strategies.BrickStrategyFactory.class.getDeclaredConstructors());
        constructorsOfProjectClasses.put("src.brick_strategies.AddPaddleStrategy",src.brick_strategies.AddPaddleStrategy.class.getDeclaredConstructors());

        projectClassPublicMethods.put("src.BrickerGameManager", src.BrickerGameManager.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.GraphicLifeCounter", src.gameobjects.GraphicLifeCounter.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.NumericLifeCounter", src.gameobjects.NumericLifeCounter.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.Ball", src.gameobjects.Ball.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.Brick", src.gameobjects.Brick.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.Paddle", src.gameobjects.Paddle.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.MockPaddle", src.gameobjects.MockPaddle.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.Puck", src.gameobjects.Puck.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.gameobjects.BallCollisionCountdownAgent", src.gameobjects.BallCollisionCountdownAgent.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.CollisionStrategy",src.brick_strategies.CollisionStrategy.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.RemoveBrickStrategyDecorator",src.brick_strategies.RemoveBrickStrategyDecorator.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.RemoveBrickStrategy",src.brick_strategies.RemoveBrickStrategy.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.PuckStrategy",src.brick_strategies.PuckStrategy.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.ChangeCameraStrategy",src.brick_strategies.ChangeCameraStrategy.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.BrickStrategyFactory",src.brick_strategies.BrickStrategyFactory.class.getDeclaredMethods());
        projectClassPublicMethods.put("src.brick_strategies.AddPaddleStrategy",src.brick_strategies.AddPaddleStrategy.class.getDeclaredMethods());

        projectClassPublicMethods.put("algo_questions.Solutions", algo_questions.Solutions.class.getDeclaredMethods());

        // check for required methods
        for (var entry : requiredMethods.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            for (String[] requiredMethod: entry.getValue())
            {
                String name = requiredMethod[NAME_INDEX];
                found = false;
                for (Method method : projectClassPublicMethods.get(entry.getKey()))
                {
                    String methodName = method.getName();
                    if (methodName.equals(name)) {
                        found = true;
                        mod = method.getModifiers();
                        if (!Modifier.isPublic(mod))
                            System.out.printf("%s_not_public%n", name);
                        returnType = method.getReturnType().getCanonicalName();
                        if (!returnType.equals(requiredMethod[RETURN_TYPE_INDEX])) {
                            System.out.printf("%s_wrong_return_type_in_class_%s%n", name, entry.getKey());
                            System.out.printf("returnType:%s%n", returnType);
                            System.out.printf("methodDetails[RETURN_TYPE_INDEX]:%s%n", requiredMethod[RETURN_TYPE_INDEX]);
                        }
                        parameterTypes = Arrays.toString(method.getParameterTypes());
                        if (!parameterTypes.equals(requiredMethod[PARAMETER_TYPES_INDEX])) {
                            System.out.printf("%s_wrong_parameter_types_in_class_%s%n", name, entry.getKey());
                            System.out.printf("parameterTypes:%s%n", parameterTypes);
                            System.out.printf("methodDetails[PARAMETER_TYPES_INDEX]:%s%n", requiredMethod[PARAMETER_TYPES_INDEX]);
                        }
                    }
                }
                if (!found) {
                    System.out.printf("%s_not_found_in_class_%s%n", name, entry.getKey());
                }
            }
        }

        // check for required constructors
        for (String[] requiredConstructor : CONSTRUCTOR)
        {
            found = false;
            for (Constructor<?> constructor:
                    constructorsOfProjectClasses.get(requiredConstructor[NAME_INDEX]))
            {
                String constructorName = constructor.getName();
                if (constructorName.equals(requiredConstructor[NAME_INDEX])) {
                    found = true;
                    mod = constructor.getModifiers();
                    if (Modifier.isPrivate(mod))
                        System.out.printf("%s_private%n", requiredConstructor[NAME_INDEX]);
                    parameterTypes = Arrays.toString(constructor.getParameterTypes());
                    if (!parameterTypes.equals(requiredConstructor[PARAMETER_TYPES_INDEX])) {
                        System.out.printf("%s_wrong_parameter_types%n", requiredConstructor[NAME_INDEX]);
                        System.out.printf("parameterTypes:%s%n", parameterTypes);
                        System.out.printf("methodDetails[PARAMETER_TYPES_INDEX]:%s%n", requiredConstructor[PARAMETER_TYPES_INDEX]);
                    }
                }
            }
            if (!found) {
                System.out.printf("%s_not_found%n", requiredConstructor[NAME_INDEX]);
            }
        }

        // check algo functions are static
        for (Method method : projectClassPublicMethods.get("algo_questions.Solutions"))
        {
            if(!Modifier.isStatic(method.getModifiers()))
                System.out.printf("%s_is_not_static_in_algo_questions.Solutions%n", method.getName());

        }

    }
}


