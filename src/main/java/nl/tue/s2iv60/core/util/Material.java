package nl.tue.s2iv60.core.util;

import com.jogamp.opengl.GL2;
import org.joml.Vector3f;

public class Material {
    public Vector3f ambient;
    public Vector3f diffuse;
    public Vector3f specular;
    public float shininess;

    public Material(Vector3f ambient, Vector3f diffuse, Vector3f specular, float shininess) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public void use(GL2 gl) {
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, new float[] { ambient.x, ambient.y, ambient.z }, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, new float[] { diffuse.x, diffuse.y, diffuse.z }, 0);
        gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_SPECULAR, new float[] { specular.x, specular.y, specular.z }, 0);
        gl.glMaterialf(GL2.GL_FRONT,GL2.GL_SHININESS, shininess);
    }
}
