package sandbox;

import com.jogamp.opengl.GLAutoDrawable;
import nl.tue.s2iv60.core.cg.Renderer;
import objects.Axis;
import objects.Cube;
import objects.Terrain;
import org.joml.Vector3f;
import static sandbox.Sandbox.ComboBoxID.VIEWMODE;
import shaders.ShaderPrograms;

/**
 * The main.java.SandboxRenderer extends the Renderer base class. The Renderer
 * superclass takes care of most OpenGL calls, but you can always edit behaviour
 * using your own calls. (e.g. enabling/disabling back face culling)
 *
 * The Renderer class holds the camera which is initialized before any objects
 * are created.
 *
 */
public class SandboxRenderer extends Renderer {

    private int currentViewMode = -1; // no known view mode

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        super.init(glAutoDrawable);

        /* Initialize the camera's position */
        camera = null;

        /* Load all the shaders */
        ShaderPrograms.setupShaders(gl, glu);
    }

    /**
     * Here you add all the objects to the scene that will be rendered. All
     * objects need to implement the interface Renderable.
     */
    @Override
    public void initObjects() {
        //objects.add(new Cube());
        objects.add(new Axis(new Vector3f(0), 1));
        objects.add(new Terrain());
    }

    /**
     * This is the main render method which calls the display method of Renderer
     * for passing camera parameters to opengl and rendering the objects array.
     * Here you can do additional things that have to be done each frame, e.g.
     * selection of the camera.
     *
     * @param glAutoDrawable
     */
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        // select new camera if view mode changed
        if (currentViewMode != VIEWMODE.getValue()) {
            // camera parameters
            boolean reset = camera == null || currentViewMode == -1;
            switch (VIEWMODE.getValue()) {
                case 0:
                    camera = new FixedCamera();
                    break;
                case 1:
                    // pickup current camera parameters and pass them on.
                    Vector3f eye = reset ? null : camera.getEye();
                    Vector3f cnt = reset ? null : camera.getCenter();
                    Vector3f up = reset ? null : camera.getUp();
                    camera = new FlyingCamera(eye, cnt, up);
                    break;
                case 2:
                    camera = new FirstPersonCamera();
                    break;
                default:
                    System.err.println(VIEWMODE.getValue());
            }
            currentViewMode = VIEWMODE.getValue();
        }

        // pass camera parameters to opengl,
        // render all objects in the objects array, ...
        super.display(glAutoDrawable);
    }

    @Override
    public void resetView() {
        currentViewMode = -1;   // resets the parameters of the current camera
    }
}
