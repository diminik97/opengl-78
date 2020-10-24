package nl.tue.s2iv60.core.app;


import java.util.*;

public class GS {
    /**
     * App variables
     */
    final public static String APP_NAME = "SANDBOX_2IV60";
    final public static int FPS = 60;

    /**
     * Screen variables
     */
    public static int FRAME_WIDTH = 1080;
    public static int FRAME_HEIGHT = 720;
    public static float FAR_PLANE = 1000;
    public static float NEAR_PLANE = 1;
    public static int FOV = 60;

    /**
     * Operating system specific variables
     */
    final public static String FS = System.getProperty("file.separator");
    private final static String WORKING_DIR = System.getProperty("user.dir")
            + FS + "src" + FS + "main" + FS;
    final public static String RESOURCES_DIR = WORKING_DIR
            + "resources" + FS;
    final public static String SHADER_DIR = RESOURCES_DIR + "shaders" + FS;
    final public static String TEX_DIR = RESOURCES_DIR + "textures" + FS;

    /**
     * Animation times
     */
    public static long startTimeOfLastFrame = System.currentTimeMillis();
    public static long startTimeOfApplication = startTimeOfLastFrame;
    public static double tAnim;   // time since start of application in seconds.

    /**
     * Camera variables
     */
    public static float vDist = 10f;        // Distance eye point to center point.
    public static float theta = 0f;         // Azimuth angle in radians.
    public static float phi = 0f;           // Inclination angle in radians.
    
    /**
     * Keyboard interaction
     */
    public static Set<Character> keysPressed = new HashSet<>();
}
