package tcgstudio2022.openMC.render.renderer;

import tcgstudio2022.openMC.GameSetting;
import tcgstudio2022.openMC.render.vertex.VertexArrayBuilder;
import tcgstudio2022.openMC.render.vertex.VertexArrayUploader;
import tcgstudio2022.openMC.util.BufferBuilder;
import tcgstudio2022.openMC.util.GLUtil;
import tcgstudio2022.openMC.util.MathHelper;
import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.render.CameraManager;
import tcgstudio2022.openMC.world.Level;
import org.lwjgl.opengl.GL11;
import tcgstudio2022.openMC.world.worldGen.synth.PerlinNoise;

import java.util.Random;

public class EnvironmentRenderer extends IWorldRenderer{
    public static final int SKY_SIZE=192;
    private static final int CLOUD_SIZE = 64;
    private static final double CLOUD_HEIGHT = 384;
    private final int skyList=GL11.glGenLists(1);
    private final int cloudList=GL11.glGenLists(1);
    private final PerlinNoise noise=new PerlinNoise(new Random(world.seed),12);

    public EnvironmentRenderer(Level w, Player p) {
        super(w, p);
        updateSky();
        updateCloud();
    }

    @Override
    public void render(float interpolationTime, int displayWidth, int displayHeight) {
        double x= MathHelper.linear_interpolate(this.player.xo,this.player.x,interpolationTime);
        double y=MathHelper.linear_interpolate(this.player.yo,this.player.y,interpolationTime);
        double z=MathHelper.linear_interpolate(this.player.zo,this.player.z,interpolationTime);
        int d=GameSetting.instance.renderDistance*256;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        CameraManager.setupPerspectiveCamera(GameSetting.instance.fov, displayWidth, displayHeight);
        GL11.glRotatef(this.player.zRot, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.player.xRot, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(this.player.yRot, 0.0f, 1.0f, 0.0f);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glCallList(this.skyList);

        for (long i = (long) (this.player.x-d)/CLOUD_SIZE; i < (this.player.x+d)/CLOUD_SIZE; i+=1) {
            for (long j = (long) (this.player.z-d)/CLOUD_SIZE; j < (this.player.z+d)/CLOUD_SIZE; j+=1) {
                if(noise.getValue(i*256,j*256)>16){
                    GL11.glPushMatrix();
                    GL11.glTranslated(i*CLOUD_SIZE-x+world.time*0.03,CLOUD_HEIGHT-y,j*CLOUD_SIZE-z);
                    GL11.glCallList(this.cloudList);
                    GL11.glPopMatrix();
                }
            }
        }
        GLUtil.setupFog(GameSetting.instance.renderDistance*512,BufferBuilder.getF(world.getFogColor()));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void updateSky(){
        int d=GameSetting.instance.renderDistance*1400;
        VertexArrayBuilder v=new VertexArrayBuilder(16777216);
        v.begin();
        int h=d/10;
        for (int i = -d; i <d ; i+=SKY_SIZE) {
            for (int j = -d; j< d; j+=SKY_SIZE) {
                int dist=Math.abs(i*j);

                v.color(this.world.getSkyColor());
                v.vertex(i,h,j+SKY_SIZE);
                v.vertex(i+SKY_SIZE, h, j+SKY_SIZE);
                v.vertex(i+SKY_SIZE, h, j);
                v.vertex(i, h, j);
                v.color(this.world.getVoidColor());
                v.vertex(i+SKY_SIZE,-h,j+SKY_SIZE);
                v.vertex(i, -h, j+SKY_SIZE);
                v.vertex(i, -h, j);
                v.vertex(i+SKY_SIZE, -h, j);
            }
        }
        v.end();
        GL11.glNewList(this.skyList,GL11.GL_COMPILE);
        VertexArrayUploader.upload(v);
        GL11.glEndList();
    }

    public void updateCloud(){
        VertexArrayBuilder vertexBuilder=new VertexArrayBuilder(400);
        vertexBuilder.begin();
        double x0 = (0);
        double x1 = (SKY_SIZE);
        double y0 = (0);
        double y1 = (6);
        double z0 =(0);
        double z1 = (SKY_SIZE);
        vertexBuilder.begin();
        vertexBuilder.color(1.0f,1.0f,1.0f,0.8f);
        vertexBuilder.vertex(x0, y0, z1);
        vertexBuilder.vertex(x0, y0, z0);
        vertexBuilder.vertex(x1, y0, z0);
        vertexBuilder.vertex(x1, y0, z1);

        vertexBuilder.vertex(x1, y1, z1);
        vertexBuilder.vertex(x1, y1, z0);
        vertexBuilder.vertex(x0, y1, z0);
        vertexBuilder.vertex(x0, y1, z1);

        vertexBuilder.vertex(x0, y1, z0);
        vertexBuilder.vertex(x1, y1, z0);
        vertexBuilder.vertex(x1, y0, z0);
        vertexBuilder.vertex(x0, y0, z0);

        vertexBuilder.vertex(x0, y1, z1);
        vertexBuilder.vertex(x0, y0, z1);
        vertexBuilder.vertex(x1, y0, z1);
        vertexBuilder.vertex(x1, y1, z1);

        vertexBuilder.vertex(x0, y1, z1);
        vertexBuilder.vertex(x0, y1, z0);
        vertexBuilder.vertex(x0, y0, z0);
        vertexBuilder.vertex(x0, y0, z1);

        vertexBuilder.vertex(x1, y0, z1);
        vertexBuilder.vertex(x1, y0, z0);
        vertexBuilder.vertex(x1, y1, z0);
        vertexBuilder.vertex(x1, y1, z1);
        vertexBuilder.end();
        GL11.glNewList(this.cloudList,GL11.GL_COMPILE);
        VertexArrayUploader.upload(vertexBuilder);
        GL11.glEndList();
    }

    @Override
    public void chunkUpdate(long x, long y, long z) {

    }
}
