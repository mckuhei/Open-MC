package com.SunriseStudio.TeamTCG.openmc.render.renderer;

import com.SunriseStudio.TeamTCG.openmc.GameSetting;
import com.SunriseStudio.TeamTCG.openmc.render.object.RenderChunkPos;
import com.SunriseStudio.TeamTCG.openmc.render.vertex.MultiRenderCompileService;
import com.SunriseStudio.TeamTCG.openmc.render.vertex.VertexArrayUploader;
import com.SunriseStudio.TeamTCG.openmc.resources.textures.TextureManagerDirect;
import com.SunriseStudio.TeamTCG.openmc.render.object.RenderChunk;
import com.SunriseStudio.TeamTCG.openmc.util.GLUtil;
import com.SunriseStudio.TeamTCG.openmc.util.collections.ArrayQueue;
import com.SunriseStudio.TeamTCG.openmc.util.collections.UUIDMap;
import com.SunriseStudio.TeamTCG.openmc.util.math.MathHelper;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Player;
import com.SunriseStudio.TeamTCG.openmc.render.culling.Frustum;
import com.SunriseStudio.TeamTCG.openmc.render.sort.DistanceSorter;
import com.SunriseStudio.TeamTCG.openmc.world.Level;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ChunkRenderer extends IWorldRenderer {
    public UUIDMap<RenderChunk, RenderChunkPos> chunks=new UUIDMap<>();
    public ArrayQueue<RenderChunk> updateQueue=new ArrayQueue<>();
    public Frustum frustum;
    public MultiRenderCompileService<RenderChunk> updateService=new MultiRenderCompileService<>(GameSetting.instance.maxDrawThread);
    public ChunkRenderer(Level w, Player p) {
        super(w, p);
    }
    public int allCount;
    public int visibleCount;
    public int updateCount;

    @Override
    public void render(float interpolationTime, int displayWidth, int displayHeight){
        allCount=0;
        visibleCount=0;
        updateCount=0;
        this.setupCamera(interpolationTime,displayWidth,displayHeight);
        TextureManagerDirect.bind("/assets/minecraft/textures/blocks/terrain.png");
        double x= MathHelper.linear_interpolate(this.player.xo,this.player.x,interpolationTime);
        double y=MathHelper.linear_interpolate(this.player.yo,this.player.y,interpolationTime);
        double z=MathHelper.linear_interpolate(this.player.zo,this.player.z,interpolationTime);
        //send chunk update
        this.updateChunks();
        //check for add chunk in distance
        this.updateQueue.addAll(this.checkForChunkAdd());
        //try to remove distance out of distance
        Iterator<RenderChunk> iterator=this.chunks.elements.iterator();
        while(iterator.hasNext()){
            RenderChunk c=iterator.next();
            double xd = Math.abs(this.player.x - c.x * 16);
            double yd = Math.abs(this.player.y - c.y * 16);
            double zd = Math.abs(this.player.z - c.z * 16);
            int distance=GameSetting.instance.renderLoadingDistance*16;
            if(xd>distance||yd>distance||zd>distance) {
                GL11.glDeleteLists(c.getRenderList(), 0);
                iterator.remove();
            }
            distance=GameSetting.instance.renderDistance*16;
            if(xd<distance&&yd<distance&&zd<distance) {
                c.render(x,y,z);
            }
            visibleCount++;
        }
        updateCount=this.updateQueue.size();
        //openGLUtil.setupFog(GameSetting.instance.renderDistance*16, BufferBuilder.getF(world.getFogColor()));
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    private void updateChunks() {
        if(updateQueue.size()>=GameSetting.instance.maxUpdatesPreFrame) {
            List<RenderChunk> updates = this.updateQueue.pollIf(GameSetting.instance.maxUpdatesPreFrame,c-> frustum.isVisible(c.getVisibleArea()));
            for (RenderChunk c : updates) {
                double xd = Math.abs(this.player.x - c.x * 16);
                double yd = Math.abs(this.player.y - c.y * 16);
                double zd = Math.abs(this.player.z - c.z * 16);
                int distance=GameSetting.instance.renderDistance*16;
                if(xd<distance&&yd<distance&&zd<distance) {
                    this.updateService.startDrawing(c);
                }
            }

        }

        //receive chunk update and draw
        try {
            if (this.updateService.getResultSize() >= GameSetting.instance.maxUpdatesPreFrame) {
                for (Future<RenderChunk> buildable : this.updateService.multiDrawResult.pollAll(GameSetting.instance.maxUpdatesPreFrame)) {
                    GL11.glNewList(buildable.get().getRenderList(), GL11.GL_COMPILE);
                    VertexArrayUploader.upload(buildable.get().getChunkBuilder());
                    GL11.glEndList();
                }
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    /**
     * setup camera,and frustum culling
     * @param interpolationTime i
     * @param displayWidth screen width
     * @param displayHeight screen height
     */
    private void setupCamera(float interpolationTime, int displayWidth, int displayHeight) {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER,0.0f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);

        //set camera and textures
        GLUtil.setupPerspectiveCamera(GameSetting.instance.fov, displayWidth, displayHeight);


        GL11.glRotated(this.player.xRot, 1, 0, 0);
        GL11.glRotated(this.player.yRot, 0, 1, 0);
        GL11.glRotated(this.player.zRot,0,0,1);
        this.frustum=Frustum.getFrustum();
    }

    /**
     * set update ut pos
     * if chunk not exist it will load the chunk
     * @param cx x chunk pos
     * @param cy y chunk pos
     * @param cz z chunk pos
     */
    public void setUpdateAt(long cx, int cy, long cz) {
        RenderChunk result = getChunk(cx, cy, cz);
        if (result == null) {
            result = new RenderChunk(world, cx, cy, cz);
            this.chunks.add(result);
        }
        this.updateQueue.add(result);
    }

    /**
     *
     * @param cx x chunk pos
     * @param cy y chunk pos
     * @param cz z chunk pos
     * @return if exist return chunk,else return null
     */
    public RenderChunk getChunk(long cx, int cy, long cz) {return this.chunks.get(new RenderChunkPos(cx,cy,cz));}

    @Override
    public void chunkUpdate(long x, long y, long z) {
        setUpdateAt(x/16, Math.toIntExact(y / 16),z/16);
    }

    /**
     * try to add chunk in distance but not exist
     * @return chunks needed to add(sorted by distance)
     */
    public ArrayList<RenderChunk> checkForChunkAdd(){
        ArrayList<RenderChunk> updates=new ArrayList<>();
        long playerCX = (long) (this.player.x / 16);
        long playerCZ = (long) (this.player.z / 16);
        int playerCY = (int) (this.player.y / 16);
        for (long cx = playerCX - GameSetting.instance.renderDistance; cx <= playerCX + GameSetting.instance.renderDistance; cx++) {
            for (long cz = playerCZ - GameSetting.instance.renderDistance; cz <= playerCZ + GameSetting.instance.renderDistance; cz++) {
                for (int cy = playerCY - GameSetting.instance.renderDistance; cy <= playerCY + GameSetting.instance.renderDistance; cy++) {
                    if (cy >= 0 && cy < 512 / 16) {
                        double xd = Math.abs(this.player.x - cx * 16);
                        double yd = Math.abs(this.player.y - cy * 16);
                        double zd = Math.abs(this.player.z - cz * 16);
                        int distance=GameSetting.instance.renderDistance*16;
                        if(xd<distance&&yd<distance&&zd<distance) {
                            if (getChunk(cx, cy, cz) == null) {
                                RenderChunk c=new RenderChunk(this.world,cx,cy,cz);
                                if(frustum.isVisible(c.getVisibleArea())) {
                                    updates.add(c);
                                    //this.chunkCache.add(c);
                                    this.chunks.add(c);
                                }
                            }
                        }
                    }
                }
            }
        }
        updates.sort(new DistanceSorter(this.player));
        return updates;
    }
}



