package tcgstudio2022.openMC.resources.textures;

import tcgstudio2022.openMC.resources.ResourcePacks;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class BasicTexture extends Texture{

    public BasicTexture(boolean ms,boolean mip) {
        super(ms,mip);
    }

    @Override
    public void startLoading() {
        IntBuffer ib = BufferUtils.createIntBuffer(1);
        ib.clear();
        GL11.glGenTextures(ib);
        this.glId = ib.get(0);
        GL11.glBindTexture(this.getType(),this.glId);
    }

    @Override
    public void load(String path){
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
        GLU.gluBuild2DMipmaps(this.getType(), GL11.GL_RGBA, this.width, this.height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
    }

    @Override
    public void endLoading() {
        //just do nothing. you can end whatever you want.
    }

    @Override
    public int getType() {
        if(this.multiSample){
            return GL32.GL_TEXTURE_2D_MULTISAMPLE;
        }else{
            return GL11.GL_TEXTURE_2D;
        }
    }
}
