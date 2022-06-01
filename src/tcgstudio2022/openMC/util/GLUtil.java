package tcgstudio2022.openMC.util;

import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

public class GLUtil {
    public static void setupFog(int distance, FloatBuffer color){
        GL11.glEnable(GL11.GL_FOG);
        GL11.glFog(GL11.GL_FOG_COLOR, color);
        GL11.glFogf(GL11.GL_FOG_DENSITY,1/(distance/1.33f));
        GL11.glHint(GL11.GL_FOG_HINT,GL11.GL_NICEST);
        GL11.glFogi(GL11.GL_FOG_MODE,GL11.GL_EXP);
    }
}
