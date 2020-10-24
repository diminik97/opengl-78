package objects;

import nl.tue.s2iv60.core.util.Material;
import nl.tue.s2iv60.core.cg.Renderable;
import nl.tue.s2iv60.core.util.TextureImg;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import static java.util.Arrays.asList;
import java.util.List;
import org.joml.Vector3f;
import static sandbox.Sandbox.CheckBoxID.SHOWCONTROLPOLYGONS;
import static sandbox.Sandbox.CheckBoxID.SHOWPATH;
import shaders.ShaderPrograms;

public class Road implements Renderable {

    private final Vector3f position;

    public Road(Vector3f position) {
        this.position = position;
    }

    @Override
    public void render(GL2 gl, GLUT glut, double tAnim, double dt) {
        if (!SHOWPATH.getValue()) {
            return;
        }
    }

   /** computes point at parameter t on Bezier spline. */
    public Vector3f getCubicBezierSplinePnt(double t){
        return null;
    }

   /** computes tangent at parameter t on Bezier spline. */
    public Vector3f getCubicBezierSplineTng(double t){
        return null;
    }

   /** computes point at parameter t on cubic Bezier with given control points P. */
   private static Vector3f getCubicBezierPnt(final Vector3f[] P, double t) {
        return null;
    }

   /** computes tangent at parameter t on cubic Bezier with given control points P. */
   private static Vector3f getCubicBezierTng(final Vector3f[] P, double t) {
        return null;
    }
}
