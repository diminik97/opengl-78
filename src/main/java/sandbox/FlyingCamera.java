package sandbox;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import nl.tue.s2iv60.core.cg.Camera;
import nl.tue.s2iv60.core.app.GS;
import org.joml.Vector3f;

class FlyingCamera extends Camera {
    @Override
    public void apply(GL2 gl, GLU glu) {
        // Compute eye vector based on center and GS.theta, GS.phi, and GS.vDist.
        // Keep up-vector the same.
        super.apply(gl, glu);
        
        // Compute the xy componennt of the eye vector
        double xy = Math.cos(GS.phi) * GS.vDist;
        
        // Compute the new eye vector using trigonometry with xy component and vDist of the eye vector.
        this.eye = new Vector3f((float) (Math.cos(GS.theta) * xy), (float) (Math.sin(GS.theta) * xy), (float) (Math.sin(GS.phi) * GS.vDist));
    }
    
    // Compute magnitude of vec3
    public float length3(float a, float b, float c){ 
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
    }
    
    /**
     * Set camera at given eye point, looking at given center, and taking the up
     * vector for the vertical direction. If one of these vectors is null, default
     * values are used.
     * @param e eye vector
     * @param c center vector
     * @param u up vector
     */
    public FlyingCamera(Vector3f e, Vector3f c, Vector3f u) {
        boolean useGiven = e!=null && c!=null && u!=null;
        this.eye    = useGiven? e : new Vector3f(-10,10,10);
        this.center = useGiven? c : new Vector3f(0,0,0); 
        this.up     = useGiven? u : new Vector3f(0,0,1);
        
        //  compute theta, phi, and vDist to start with same eye point and direction
        GS.phi = (float) Math.atan2(eye.z, length3(eye.x, eye.y, 0));
        GS.vDist = length3(eye.x, eye.y, eye.z);
        GS.theta = - (float) Math.acos(eye.x / length3(eye.x, eye.y, 0));
    }
}