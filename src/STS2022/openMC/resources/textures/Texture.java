package tcgstudio2022.openMC.resources.textures;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public abstract class Texture {
    protected int glId;
    protected int width;
    protected int height;
    protected final boolean multiSample;
    protected final boolean mipMap;

    public Texture(boolean multiSample,boolean mipMap){
        this.multiSample=multiSample;
        this.mipMap=mipMap;
    }

    //operation
    public void bind() {
        GL11.glBindTexture(this.getType(),this.glId);
    }

    public void createMipMap() {
        GL11.glBindTexture(this.getType(),this.glId);
        GL30.glGenerateMipmap(this.getType());
        GL11.glTexParameteri(this.getType(), GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR_MIPMAP_LINEAR);
        GL11.glTexParameteri(this.getType(), GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
    }

    //load
    public abstract void startLoading();

    public abstract void load(String path);

    public abstract void endLoading();

    //meta
    public abstract int getType();
}
