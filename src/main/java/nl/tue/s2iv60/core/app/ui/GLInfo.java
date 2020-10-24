package nl.tue.s2iv60.core.app.ui;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

/**
 * Checks the OpenGL / shading language versions and queries which extensions
 * are available.
 * 
 * @author Justin Stoecker
 */
public class GLInfo {

   private List<String> basicInfo;
   String extensions;

   public GLInfo(GL gl) {

      List<String> b = new ArrayList<>();

      b.add(format("-%-25s: %s", "GL Version", gl.glGetString(GL.GL_VERSION)));
      if (gl instanceof GL2ES2) {
         b.add(format("-%-25s: %s", "SL Version", gl.glGetString(GL2ES2.GL_SHADING_LANGUAGE_VERSION)));
      }
      b.add(format("-%-25s: %s", "Vendor", gl.glGetString(GL.GL_VENDOR)));
      b.add(format("-%-25s: %s", "Renderer", gl.glGetString(GL.GL_RENDERER)));
      basicInfo = b;

      extensions = gl.glGetString(GL.GL_EXTENSIONS);
   }

   /**
    * Checks if a particular extension is supported by the client's OpenGL
    * implementation.
    */
   public boolean extSupported(String ext) {
      return extensions == null ? false : extensions.indexOf(ext) != -1;
   }

   /**
    * Prints out basic info about client's OpenGL implementation.
    */
   public List<String> getBasicInfo() {
      return basicInfo;
   }

   /**
    * List all OpenGL extensions supported by the client
     * @return list of extensions
    */
   public List<String> getExtensionsInfo() {
      return Arrays.asList(extensions.split(" "));
   }
}
