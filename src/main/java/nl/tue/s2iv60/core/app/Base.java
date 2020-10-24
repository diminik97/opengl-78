package nl.tue.s2iv60.core.app;

import nl.tue.s2iv60.core.app.ui.MainFrame;
import nl.tue.s2iv60.core.cg.Renderer;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Base {

    private GLAutoDrawable drawable;
    private FPSAnimator animator;
    private MainFrame mainFrame;

    public Base() {
        GLProfile.initSingleton();
        initJOGL();
    }

    /**
     * Initialization of JOGL
     */
    private void initJOGL() {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(profile);
       
//        GLCanvas canvas = new GLCanvas(cap); // Is a bit faster
        GLJPanel canvas = new GLJPanel(cap);   // works better with swing
        
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                     canvas.requestFocusInWindow();
                }
            }
            
        });
        mainFrame = new MainFrame(canvas);
        
        this.drawable = canvas;
        animator = new FPSAnimator(drawable, GS.FPS, true);
    }

    public void setRenderer(Renderer renderer) {
        drawable.addGLEventListener(renderer);
        mainFrame.setRenderer(renderer);
    }

    protected void start() {
        animator.start();
    }
    
    public static void reportError(String prefix, GL gl, GLU glu) {
        // Report OpenGL errors.
        int errorCode = gl.glGetError();
        while(errorCode != GL.GL_NO_ERROR) {
            System.err.format("%s:%d: %s\n",prefix,errorCode,glu.gluErrorString(errorCode));
            errorCode = gl.glGetError();
        }
    }
}