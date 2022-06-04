package com.SunriseStudio.TeamTCG.openmc.resources.textures;

import com.SunriseStudio.TeamTCG.openmc.resources.ResourcePacks;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

public class GroupedTextures extends Texture{
    public HashMap<String,Integer> textureMapping;
    public int prevLayer=0;

    public GroupedTextures(boolean ms,boolean mip) {
        super(ms,mip);
    }

    @Override
    public void startLoading(){
        IntBuffer ib = BufferUtils.createIntBuffer(1);
        ib.clear();
        GL11.glGenTextures(ib);
        this.glId = ib.get(0);
        GL11.glBindTexture(this.getType(),this.glId);
    }

    @Override
    public void load(String path) {
        BufferedImage img= null;
        try {
            img = ImageIO.read(ResourcePacks.instance.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width=img.getWidth();
        this.height=img.getHeight();

        int[] rawPixels =new int[this.width*this.height];
        byte[] newPixels = new byte[rawPixels.length * 4];
        ByteBuffer pixels = BufferUtils.createByteBuffer(rawPixels.length*4);
        img.getRGB(0, 0, this.width , this.height, rawPixels, 0, this.width);
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
        GL12.glTexSubImage3D(this.getType(), 0, 0, 0, this.prevLayer,width, height, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
        this.textureMapping.put(path,prevLayer);
        prevLayer++;
    }

    public void endLoading(){
        //do nth.
    }

    @Override
    public int getType() {
        if(this.multiSample){
            return GL32.GL_TEXTURE_2D_MULTISAMPLE_ARRAY;
        }else{
            return GL30.GL_TEXTURE_2D_ARRAY;
        }
    }

    @Override
    public void createMipMap() {
        this.bind();
        GL30.glGenerateMipmap(GL30.GL_TEXTURE_2D_ARRAY);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR_MIPMAP_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
    }
}
