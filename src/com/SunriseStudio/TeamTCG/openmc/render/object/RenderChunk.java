package com.SunriseStudio.TeamTCG.openmc.render.object;

import com.SunriseStudio.TeamTCG.openmc.render.sort.DistanceComparable;
import com.SunriseStudio.TeamTCG.openmc.render.vertex.VertexArrayBuilder;
import com.SunriseStudio.TeamTCG.openmc.render.vertex.VertexArrayCompileCallable;
import com.SunriseStudio.TeamTCG.openmc.util.collections.UUIDGetter;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;
import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;
import com.SunriseStudio.TeamTCG.openmc.world.Level;
import com.SunriseStudio.TeamTCG.openmc.world.block.Tile;
import org.lwjgl.opengl.GL11;

public class RenderChunk implements VertexArrayCompileCallable , DistanceComparable , UUIDGetter<RenderChunkPos> {

    public boolean empty;
    public long x;
    public int y;
    public long z;
    public Level world;

    private AABB visibleArea;
    private int renderList=GL11.glGenLists(1);
    private VertexArrayBuilder chunkBuilder=new VertexArrayBuilder(131072);

    public RenderChunk(Level w, long x, int y, long z){
        this.world=w;
        this.x=x;
        this.y=y;
        this.z=z;
        this.visibleArea=new AABB(x,y,z,x+16,y+16,z+16);
    }


    public void update(){
        this.chunkBuilder.begin();
        for (int cx = 0; cx < 16; ++cx) {
            for (int cy = 0; cy <16; ++cy) {
                for (int cz = 0; cz < 16; ++cz) {
                    Tile renderTile = Tile.tiles[RenderChunk.this.world.getTile(cx+x*16, cy+y*16, cz+z*16 )];
                    if (renderTile != null){
                        RenderChunk.this.empty = false;
                        renderTile.render(this.chunkBuilder, RenderChunk.this.world, 0, cx, cy, cz,cx+x*16, cy+y*16, cz+z*16);
                    }
                }
            }
        }
        this.chunkBuilder.end();
    }

    public AABB getVisibleArea() {
        return this.visibleArea;
    }

    public VertexArrayBuilder getChunkBuilder() {
        return this.chunkBuilder;
    }

    public int getRenderList() {
        return this.renderList;
    }

    public void render(double camX,double camY,double camZ){
        GL11.glPushMatrix();
        GL11.glTranslated(x*16-camX,y*16-camY,z*16-camZ);
        GL11.glCallList(this.renderList);
        GL11.glPopMatrix();
    }

    public final double distanceTo(Entity target) {
        double x = Math.abs(target.x - this.x * 16);
        double y = Math.abs(target.y - this.y * 16);
        double z = Math.abs(target.z - this.z * 16);
        return x*y*z;
    }

    @Override
    public int getList() {
        return this.renderList;
    }

    @Override
    public VertexArrayBuilder compile() {
        update();
        return this.chunkBuilder;
    }

    @Override
    public boolean equalsAnother(UUIDGetter<RenderChunkPos> uid) {
        return this.getUID().compare(uid.getUID());
    }

    @Override
    public RenderChunkPos getUID() {
        return new RenderChunkPos(this.x,this.y,this.z);
    }
}
