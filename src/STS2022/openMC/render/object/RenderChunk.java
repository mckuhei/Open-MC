package tcgstudio2022.openMC.render.object;

import tcgstudio2022.openMC.render.vertex.VertexArrayBuilder;
import tcgstudio2022.openMC.render.vertex.VertexBuildable;
import tcgstudio2022.openMC.util.ArrayQueue;
import tcgstudio2022.openMC.util.Pair;
import tcgstudio2022.openMC.world.entity.Entity;
import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.render.sort.DistanceComparable;
import tcgstudio2022.openMC.util.phys.AABB;
import tcgstudio2022.openMC.world.Level;
import tcgstudio2022.openMC.world.block.Tile;
import org.lwjgl.opengl.GL11;

public class RenderChunk implements
        IRenderChunk {

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


    @Override
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

    @Override
    public AABB getVisibleArea() {
        return this.visibleArea;
    }

    @Override
    public VertexArrayBuilder getChunkBuilder() {
        return this.chunkBuilder;
    }

    @Override
    public int getRenderList() {
        return this.renderList;
    }

    @Override
    public void render(double camX,double camY,double camZ){
        GL11.glPushMatrix();
        GL11.glTranslated(x*16-camX,y*16-camY,z*16-camZ);
        GL11.glCallList(this.renderList);
        GL11.glPopMatrix();
    }

    @Override
    public final double distanceTo(Entity target) {
        double x = Math.abs(target.x - this.x * 16);
        double y = Math.abs(target.y - this.y * 16);
        double z = Math.abs(target.z - this.z * 16);
        return x*y*z;
    }
}
