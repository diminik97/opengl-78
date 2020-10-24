package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWAXIS;
import shaders.ShaderPrograms;

public class Axis implements Renderable {
    private Vector3f pos;
    private float sf;

    public Axis(Vector3f pos, float sf) {
        this.pos = pos;
        this.sf = sf;
    }
    
    // Method drawArrow, draws positive axis as arrow and the according negative axis as cylinder
    // Parameter length represents arrow length and
    // radius sphere radius
    public void drawArrow(GL2 gl, GLUT glut, double length, double radius, float r, float g, float b){
        // Arrowbody
        gl.glPushMatrix();
        gl.glColor3f(r, g, b);
        glut.glutSolidCylinder(0.4 * radius, 0.9 * length, 32, 32);
        gl.glPopMatrix();

        // Arrowhead
        gl.glPushMatrix();
        gl.glColor3f(r, g, b);
        gl.glTranslated(0, 0, 0.8f);
        glut.glutSolidCone(radius, 0.2 * length, 32, 32);
        gl.glPopMatrix();
        
        //Negative axis
        gl.glPushMatrix();
        gl.glColor3f(r, g, b);
        glut.glutSolidCylinder(0.1 * radius, -length, 32, 32);
        gl.glPopMatrix();
    }
    
    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        final int length = 1;     //Axis length
        final double radius_Sphere = 0.15;
        
        if (SHOWAXIS.getValue()) {
            ShaderPrograms.useDefaultShader(gl);

            // Create z-axis
            gl.glPushMatrix();
            drawArrow(gl, glut, length, radius_Sphere, 0, 0, 1);
            gl.glPopMatrix();
            
            // Create x-axis
            gl.glPushMatrix();
            gl.glRotatef(90, 0, 1, 0);
            drawArrow(gl, glut, length, radius_Sphere, 1, 0, 0);
            gl.glPopMatrix();
            
            // Create y-axis
            gl.glPushMatrix();
            gl.glRotatef(-90, 1, 0, 0);
            drawArrow(gl, glut, length, radius_Sphere, 0, 1, 0);
            gl.glPopMatrix();
            
            // Create yellow sphere at origin
            gl.glPushMatrix();
            gl.glColor3f(1, 1, 0);
            glut.glutSolidSphere(radius_Sphere, 62, 62);
            gl.glPopMatrix();
        }
    }
}
