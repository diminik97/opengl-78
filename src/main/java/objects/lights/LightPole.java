package objects.lights;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;
import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWLIGHTPOLES;
import static sandbox.Sandbox.ComboBoxID.DAYNIGHT;
import shaders.ShaderPrograms;

public class LightPole implements Renderable {
    private final Vector3f POS, FACING;
    private final float SF;
    private final Integer LIGHT;    // GL_LIGHTi, null ==> no light source

    public LightPole(Vector3f pos, Vector3f tng, float sf, Integer LIGHT) {
        this.POS = pos;
        this.SF = sf;
        this.FACING = tng;
        this.LIGHT = LIGHT;
    }
    
    public LightPole(Vector3f pos, float sf, Integer LIGHT) {
        this(pos, new Vector3f(1,0,0), sf, LIGHT);
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (SHOWLIGHTPOLES.getValue()) {
        }
    }
}
