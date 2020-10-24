package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import nl.tue.s2iv60.core.cg.Camera;
import nl.tue.s2iv60.core.app.GS;
import org.joml.Vector3f;

class FirstPersonCamera extends Camera {
    public FirstPersonCamera() {
        this.eye = new Vector3f(-6, -15, 1.8f);       // eys at 1.8 meter
        this.center = new Vector3f(-8, -15, 1.8f);
        this.up = new Vector3f(0, 0, 1);
    }
    
    @Override
    public void apply(GL2 gl, GLU glu) {
        super.apply(gl, glu);
    }
}
