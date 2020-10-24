package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWTERRAIN;
import shaders.ShaderPrograms;

public class Terrain implements Renderable {

    private final double sizeX = 50;  // meter
    private final double sizeY = 50;    // meter
    private final double sizeZ = 1;  // meter

    public Terrain() {

    }

    // Draws the Terrain with 50x50m surface and 1m depth as required
    // Color is set to green as required
    public void drawTerrain(GL2 gl, GLUT glut) {
        //drawing terrain
        gl.glPushMatrix();
        gl.glScaled(50, 50, 1);
        gl.glTranslated(0, 0, -0.5);
        gl.glColor3f(0, 0.5f, 0);
        glut.glutSolidCube(1);
        gl.glPopMatrix();

    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWTERRAIN.getValue()) {
            ShaderPrograms.useDefaultShader(gl);

            gl.glPushMatrix();
            drawTerrain(gl, glut);
            gl.glPopMatrix();
        }
    }
}
