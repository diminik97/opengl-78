package objects.lights;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.app.Base;
import nl.tue.s2iv60.core.cg.Renderable;
import objects.Road;
import org.joml.Vector3f;

public class StreetLights implements Renderable {
    private final int numLights = 5;
    private final Road road;
    private final LightPole[] poles = new LightPole[numLights];

    public StreetLights(Road road) {
        this.road = road;
        
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        GLU glu = new GLU();
        
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, new float[]{1, 1, 1, 1}, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, new float[]{1, 1, 1, 1}, 0);
     
        for (int i = 0; i < numLights; i++) {
            if (poles[i]==null) {
                // position street lights regularly
                Vector3f pos = new Vector3f(0);
                Vector3f tng = new Vector3f(0);

                poles[i] = new LightPole(pos, tng, 1, gl.GL_LIGHT0+(i+1));
            }
        
            poles[i].render(gl, glut, tAnim, dt);
            Base.reportError("Opengl error in java class " + poles[i].getClass().getName(), gl, glu);
        }
    }
}
