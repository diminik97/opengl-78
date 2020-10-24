package nl.tue.s2iv60.core.cg;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import org.joml.Vector3f;
/**
 * The Camera class represents the camera in the scene.
 * It has the following fields:
 *      - eye: the world coordinates of the position of the camera
 *      - center: the world coordinates of the point the camera is looking at
 *      - up: the up vector
 * Changing these vectors will change the way the camera is oriented.
 * 
 * Light source LIGHT0 is set at a position relative to the camera's eye.
 *
 * @author kafoe
 */
public abstract class Camera {
    /** eye point. **/
    protected Vector3f eye;    
    
    /** up vector. **/
    protected Vector3f up;      
    
    /** center point. **/
    protected Vector3f center;

    /** passes camera parameters on to OpenGL; also sets head light.
     * @param gl
     * @param glu
     **/
    public void apply(GL2 gl, GLU glu) {
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        // Here camera has default position (0,0,0), 
        // points along negative z-axis
        // and its up vector is (0,1,0).

        /** set head light slightly above the eye **/
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, new float[]{0, 0.3f, 0, 1}, 0);

//      System.err.format("eye=%s, center=%s, up=%s\n",eye,center,up);
        
        glu.gluLookAt(
                eye.x,    eye.y,    eye.z,
                center.x, center.y, center.z,
                up.x,     up.y,     up.z
        );
    }

    public Vector3f getEye   () { return eye;    }
    public Vector3f getUp    () { return up;     }
    public Vector3f getCenter() { return center; }   
}
