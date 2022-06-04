package com.SunriseStudio.TeamTCG.openmc.render.vertex;

import org.lwjgl.opengl.GL11;

public class VertexArrayUploader {
    public static void upload(VertexArrayBuilder builder){
        if (builder.getVertices() > 0) {
            GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
            GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);

            GL11.glInterleavedArrays(GL11.GL_T4F_C4F_N3F_V4F, 0, builder.buffer);
            GL11.glDrawArrays(7, 0, builder.getVertices());

            GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
            GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
            GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        }
    }
}
