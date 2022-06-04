package com.SunriseStudio.TeamTCG.openmc.world.chunk;

import com.SunriseStudio.TeamTCG.openmc.util.collections.UUIDGetter;
import com.SunriseStudio.TeamTCG.openmc.world.block.Block;
import com.SunriseStudio.TeamTCG.openmc.world.block.BlockFacing;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.IBlockMaterial;
import com.SunriseStudio.TeamTCG.openmc.world.block.registery.BlockMap;
import com.SunriseStudio.TeamTCG.openmc.world.dim.IDimension;

public class Chunk implements UUIDGetter<ChunkPos> {
    private final long x,z;
    public static final int WIDTH=16;
    private final Block[][][] blocks;
    public IDimension dimension;
    public ChunkLoadTicket ticket=new ChunkLoadTicket(ChunkLoadLevel.None_TICKING,0);

    public Chunk(IDimension dimension,long x,long z){
        this.blocks=new Block[WIDTH][dimension.getDimHeight()][WIDTH];
        this.x=x;
        this.z=z;
        for (int xd = 0; xd < WIDTH; xd++) {
            for (int yd = 0; yd < this.dimension.getDimHeight(); yd++) {
                for (int zd = 0; zd < WIDTH; zd++) {
                    blocks[xd][yd][zd]=new Block(xd+x*16,yd,zd+z*16, BlockMap.getByID("air"));
                }
            }
        }
    }

    public void setBlock(int x, int y, int z, IBlockMaterial material, BlockFacing f){
        if(x>=0&&y>=0&&z>=0&&x<WIDTH&&y<dimension.getDimHeight()&&z<WIDTH) {
            blocks[x][y][z].setMaterial(material);
            blocks[x][y][z].setFacing(f);
        }
    }

    public Block getBlock(int x, int y, int z) {
        if(x>=0&&y>=0&&z>=0&&x<WIDTH&&y<dimension.getDimHeight()&&z<WIDTH)
            return blocks[x][y][z];
        else
            return null;
    }

    @Override
    public boolean equalsAnother(UUIDGetter<ChunkPos> uid) {
        return uid.getUID().compare(this.getUID());
    }

    @Override
    public ChunkPos getUID() {
        return new ChunkPos(x,z);
    }

    public void tick(){
        if(this.ticket.getTime()>0){
            if(this.ticket.getChunkLoadLevel()==ChunkLoadLevel.BlockEntity_TICKING){

            }
            if(this.ticket.getChunkLoadLevel()==ChunkLoadLevel.Block_TICKING){

            }
        }
    }
}
