package nl.tue.s2iv60.core.cg;

import static com.jogamp.opengl.GL.GL_BACK;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_CULL_FACE;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_FRONT_AND_BACK;
import nl.tue.s2iv60.core.app.GS;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2GL3.GL_FILL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.ArrayList;
import java.util.List;
import nl.tue.s2iv60.core.app.Base;
import nl.tue.s2iv60.core.app.Base;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.app.ui.GLInfo;
import sandbox.Sandbox.ComboBoxID;

public abstract class Renderer implements GLEventListener {
    private GLInfo glInfo;
    protected Camera camera;
    protected GL2 gl;
    protected GLU glu;
    protected GLUT glut;
    protected List<Renderable> objects;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();
        glInfo = new GLInfo(gl);
        glu = new GLU();
        glut = new GLUT();

        // Enable depth testing.
        gl.glEnable(GL_DEPTH_TEST);

        // Enable backface culling
        gl.glEnable(GL_CULL_FACE);
        gl.glCullFace(GL_BACK);

        // Normalize normals.
        gl.glEnable(GL2.GL_NORMALIZE);

        setView(gl, glu);

        objects = new ArrayList<>();
        initScene();
        initObjects();
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        long now = System.currentTimeMillis();
        float dt = (now-GS.startTimeOfLastFrame)/1000f;        // render time of last frame
        
        GS.tAnim = (now-GS.startTimeOfApplication)/1000d;     // in seconds

        /** set camera parameters in opengl; also positions head light. */
        camera.apply(gl, glu);

        if (ComboBoxID.DAYNIGHT.getValue()==0) {           
            gl.glClearColor(0.53f, 0.81f, 0.82f, 1f);     // sky blue during day time
        } else {
            gl.glClearColor(20/255f,24/255f,82/255f, 1f); // night sky blue
        }

        gl.glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);

        gl.glColor3f(0f, 0f, 0f);

        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

        gl.glViewport(0, 0, GS.FRAME_WIDTH, GS.FRAME_HEIGHT);

        gl.glEnable(GL_LIGHTING);

        /*
         * Render all objects
         */
        for (Renderable object : objects) {
            object.render(gl, glut, GS.tAnim, dt);
            Base.reportError("Opengl error in java class " + object.getClass().getName(), gl, glu);
        }
        
        GS.startTimeOfLastFrame = now;
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y,
                        int width, int height) {
        GS.FRAME_WIDTH = width;
        GS.FRAME_HEIGHT = height;

        setView(gl, glu);
    }

    private void setView(GL2 gl, GLU glu) {
        // Select part of window.
        gl.glViewport(0, 0, GS.FRAME_WIDTH, GS.FRAME_HEIGHT);

        // Set projection matrix.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Set the perspective.
        glu.gluPerspective(GS.FOV, GS.FRAME_WIDTH / (float)GS.FRAME_HEIGHT, 0.1f * GS.NEAR_PLANE, GS.FAR_PLANE);
    }

    public abstract void initObjects();

    /**
     * Here you can add main.java.objects you want to always appear in the scene.
     * These are added before any other main.java.objects and are therefore rendered first.
     */
    private void initScene() {
        // objects.add(new Something());
    }

    public GLInfo getGlInfo() {
        return this.glInfo;
    }

    public void resetView() {
    }
}
