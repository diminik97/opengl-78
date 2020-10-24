package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWCAROUSEL;

public class Carousel implements Renderable {
    private final float size;

    private final Vector3f position;


    public Carousel(Vector3f position, float size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (!SHOWCAROUSEL.getValue()) {
            return;
        }
    }
}
