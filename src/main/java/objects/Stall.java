package objects;

import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import objects.lights.LightPole;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWSTALL;
import shaders.ShaderPrograms;

public class Stall implements Renderable {
    private final Vector3f position;

    public Stall(Vector3f position) {
        this.position = position;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (!SHOWSTALL.getValue()) {
            return;
        }
    }
}