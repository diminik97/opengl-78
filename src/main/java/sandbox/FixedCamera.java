package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Camera;
import org.joml.Vector3f;

class FixedCamera extends Camera {
    public FixedCamera() {  
        // initialize camera parameters eye, center, and up
        eye = new Vector3f(+10,-10, +10);
        up = new Vector3f(0, 0, 1);
        center = new Vector3f(0);
    }

    @Override
    public void apply(GL2 gl, GLU glu) {
        // nothing else to do here
        super.apply(gl, glu);
    }
    
}
