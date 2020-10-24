package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.cg.Renderable;
import static sandbox.Sandbox.ComboBoxID.VIEWMODE;
import static sandbox.Sandbox.SliderID.SLIDER1;
import static sandbox.Sandbox.SliderID.SLIDER2;
import shaders.ShaderPrograms;

/**
 * Example object
 *
 * It uses 'dropdown1' to conditionally render the cube. It uses 'slider1' and
 * 'slider2' to set its position.
 *
 * In the render function, you can use all OpenGL methods. Make sure you wrap
 * the render function in these two methods:
 *
 * gl.glPushMatrix(); ... gl.glPopMatrix();
 *
 * This way none of the other main.java.objects are affected by the matrix
 * operations needed for this object.
 *
 */
public class Cube implements Renderable {

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {

        ShaderPrograms.useDefaultShader(gl);

        float slider1 = (float) SLIDER1.getValue() - 50;
        float slider2 = (float) SLIDER2.getValue() - 50;

        gl.glPushMatrix();
        gl.glScaled(50, 50, 1);
        gl.glTranslated(0, 0, -0.5);
        gl.glColor3f(0, 1, 0);
        glut.glutSolidCube(1);
        gl.glPopMatrix();

    }
}
