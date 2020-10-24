package nl.tue.s2iv60.core.util;

import nl.tue.s2iv60.core.app.GS;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.Texture;

import java.io.File;
import java.io.IOException;

public class TextureImg {

    private Texture texture;

    public TextureImg(String path) {
        texture = loadTexture(path);
    }

    private Texture loadTexture(String file) {
        Texture result = null;

        try {
            // Try to load from local folder.
            result = TextureIO.newTexture(new File(GS.TEX_DIR + file), false);

        } catch(GLException | IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    public void bind(GL2 gl) {
        texture.enable(gl);
        texture.bind(gl);
    }
}
