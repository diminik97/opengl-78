package shaders;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static nl.tue.s2iv60.core.app.GS.FS;
import static nl.tue.s2iv60.core.app.GS.SHADER_DIR;
import nl.tue.s2iv60.core.util.ShaderProgram;



/**
 * Here you can add Shaders to the project.
 *
 * To set up a shader and finally use it, four things have to be done:
 * <ol>
 * <li>Add a static field of type ShaderProgram, e.g.: <code>private static ShaderProgram myShader</code></li>
 * <li>Initialize this field in the method <code>setupShaders</code>; see example for <code>defaultShader</code>.</li>
 * <li>add a method <code>useMyShader</code>, see example code for <code>useDefaultShader</code>. 
 * Note that in the <code>useMyShader</code> method you can set uniforms that can be used in your shaders;
 * e.g. with the following statement you can pass on a boolean <code>isDay</code> to shader program:</li>
 * <ul><code>myShader.setUniform(gl, "isDay", DAYNIGHT.getValue()==0);</code></ul>
 * <li> Call the <code>useMyShader</code> method to start using the created shader.</li>
 * </ol>
 *For convenience SHADER_DIR and FS from the global state GS are used to specify 
 * the paths to the shaders. Note that FS gives the correct file separator for 
 * your operating system; not using it might get you into trouble.
 */
public class ShaderPrograms {
     /* To globally use every shader anywhere, add them like a static variable as done with defaultShader */
    private static ShaderProgram defaultShader;

    public static void setupShaders(GL2 gl, GLU glu) {
        defaultShader = createShaderProgram(gl, glu, "default",
            "default_shader", "default_vertex.glsl", null, "default_fragment.glsl"
        );
    }
    
    private static ShaderProgram createShaderProgram(
            GL2 gl, GLU glu,
            final String shaderName,
            final String shaderFolder, 
            final String vertexShader, 
            final String geometryShader,
            final String fragmentShader) {
        String folder = SHADER_DIR + shaderFolder + FS;
        return new ShaderProgram(shaderName, gl, glu,
                getFileStream(folder + vertexShader),
                getFileStream(folder + geometryShader), 
                getFileStream(folder + fragmentShader)
        );
    }

    private static String[] getFileStream(String shader) {
        try {
            return new String[] {new String(Files.readAllBytes(Paths.get(shader)))};
        } catch (IOException e) {
            return null;
        }
    }
    
    /** start using the default shader. Here you can also set your uniforms. */
    public static ShaderProgram useDefaultShader(GL2 gl) {
        defaultShader.useProgram(gl);
        return defaultShader;
    }
}