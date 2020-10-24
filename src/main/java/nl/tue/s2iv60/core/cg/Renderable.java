package nl.tue.s2iv60.core.cg;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public interface Renderable {
    /**
     *   render object.
     * @param gl
     * @param glut
     * @param tAnim  time since begin of application in seconds
     * @param dt     time since last frame has been rendered in seconds
     */
    void render(GL2 gl, GLUT glut, double tAnim, double dt);
}
