package com.SunriseStudio.TeamTCG.openmc.resources.textures;


import com.SunriseStudio.TeamTCG.openmc.util.LogHandler;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

public class TextureManagerDirect {
    private static HashMap<String, ImageData> idMap = new HashMap();
    public static void bind(String file) {
        if(idMap.containsKey(file)){
            GL11.glBindTexture(3553,idMap.get(file).id);
        }else{
            try {
                load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static ImageData getData(String file) {
        return idMap.getOrDefault(file, null);
    }

    public static class ImageData {
        public int id;
        public int width;
        public  int height;
    }


    public static int load(String resourceName) throws IOException {
        LogHandler.log("load:"+resourceName, LogHandler.Errors.INFO,"resource-manager");
        IntBuffer ib = BufferUtils.createIntBuffer(1);
        ib.clear();
        GL11.glGenTextures(ib);
        int id = ib.get(0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST_MIPMAP_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        BufferedImage img;
        try {
            img = ImageIO.read(TextureManagerDirect.class.getResourceAsStream(resourceName));
        } catch (Exception e) {
            LogHandler.log("file not exist:"+resourceName, LogHandler.Errors.WARNING,"TextureManager");
            img=ImageIO.read(TextureManagerDirect.class.getResourceAsStream("/assets/minecraft/textures/fallback.png"));
        }
        int w = img.getWidth();
        int h = img.getHeight();
        ImageData imgData=new ImageData();

        ByteBuffer pixels = BufferUtils.createByteBuffer(16777216);
        int[] rawPixels = new int[w * h];
        byte[] newPixels = new byte[w * h * 4];
        img.getRGB(0, 0, w, h, rawPixels, 0, w);
        for (int i = 0; i < rawPixels.length; ++i) {
            int a = rawPixels[i] >> 24 & 0xFF;
            int r = rawPixels[i] >> 16 & 0xFF;
            int g = rawPixels[i] >> 8 & 0xFF;
            int b = rawPixels[i] & 0xFF;
            newPixels[i * 4] = (byte)r;
            newPixels[i * 4 + 1] = (byte)g;
            newPixels[i * 4 + 2] = (byte)b;
            newPixels[i * 4 + 3] = (byte)a;
        }
        pixels.put(newPixels);
        pixels.position(0);
        GLU.gluBuild2DMipmaps(3553, 6408, w, h, 6408, 5121, pixels);
        GL30.glGenerateMipmap(3553);
        imgData.width=w;
        imgData.height=h;
        imgData.id=id;
        idMap.put(resourceName,imgData);
        return id;
    }
}


