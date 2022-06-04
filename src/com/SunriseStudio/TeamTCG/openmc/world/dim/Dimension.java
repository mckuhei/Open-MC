package com.SunriseStudio.TeamTCG.openmc.world.dim;

import com.SunriseStudio.TeamTCG.openmc.world.block.Block;
import com.SunriseStudio.TeamTCG.openmc.world.block.BlockFacing;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.IBlockMaterial;
import com.SunriseStudio.TeamTCG.openmc.world.chunk.ChunkLoadTicket;
import com.SunriseStudio.TeamTCG.openmc.world.chunk.ChunkPos;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;
import com.SunriseStudio.TeamTCG.openmc.util.collections.UUIDMap;
import com.SunriseStudio.TeamTCG.openmc.util.math.HitResult;
import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;
import com.SunriseStudio.TeamTCG.openmc.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Iterator;

public class Dimension implements IDimension{
    public UUIDMap<Chunk, ChunkPos> chunks;
    public ArrayList<Entity> entities;
    public int dimHeight=512;

    //c="vec3f"
    public HitResult clip(Vec3f var1, Vec3f var2) {
        if (!Float.isNaN(var1.x) && !Float.isNaN(var1.y) && !Float.isNaN(var1.z)) {
            if (!Float.isNaN(var2.x) && !Float.isNaN(var2.y) && !Float.isNaN(var2.z)) {
                int var2_x = (int)Math.floor((double)var2.x);
                int var2_y = (int)Math.floor((double)var2.y);
                int var2_z = (int)Math.floor((double)var2.z);
                int var1_x = (int)Math.floor((double)var1.x);
                int var1_y = (int)Math.floor((double)var1.y);
                int var1_z = (int)Math.floor((double)var1.z);
                int var9 = 20;

                while(var9-- >= 0) {
                    if (Float.isNaN(var1.x) || Float.isNaN(var1.y) || Float.isNaN(var1.z)) {
                        return null;
                    }

                    if (var1_z == var2_x && var1_y == var1_y && var1_z == var2_z) {
                        return null;
                    }

                    float x = 999.0F;
                    float y = 999.0F;
                    float z = 999.0F;
                    if (var2_x > var1_z) {
                        x = (float)var1_z + 1.0F;
                    }

                    if (var2_x < var1_z) {
                        x = (float)var1_z;
                    }

                    if (var1_y > var1_y) {
                        y = (float)var1_y + 1.0F;
                    }

                    if (var1_y < var1_y) {
                        y = (float)var1_y;
                    }

                    if (var2_z > var1_z) {
                        z = (float)var1_z + 1.0F;
                    }

                    if (var2_z < var1_z) {
                        z = (float)var1_z;
                    }

                    float x2 = 999.0F;
                    float y2 = 999.0F;
                    float z2 = 999.0F;
                    float distX = var2.x - var1.x;
                    float distY = var2.y - var1.y;
                    float distZ = var2.z - var1.z;
                    if (x != 999.0F) {
                        x2 = (x - var1.x) / distX;
                    }

                    if (y != 999.0F) {
                        y2 = (y - var1.y) / distY;
                    }

                    if (z != 999.0F) {
                        z2 = (z - var1.z) / distZ;
                    }

                    boolean var19 = false;
                    byte facing;
                    if (x2 < y2 && x2 < z2) {
                        if (var2_x > var1_z) {
                            facing = 4;
                        } else {
                            facing = 5;
                        }

                        var1.x = x;
                        var1.y += distY * x2;
                        var1.z += distZ * x2;
                    } else if (y2 < z2) {
                        if (var1_y > var1_y) {
                            facing = 0;
                        } else {
                            facing = 1;
                        }

                        var1.x += distX * y2;
                        var1.y = y;
                        var1.z += distZ * y2;
                    } else {
                        if (var2_z > var1_z) {
                            facing = 2;
                        } else {
                            facing = 3;
                        }

                        var1.x += distX * z2;
                        var1.y += distY * z2;
                        var1.z = z;
                    }

                    Vec3f position;
                    var1_z = (int)((position = new Vec3f(var1.x, var1.y, var1.z)).x = (float)Math.floor((double)var1.x));
                    if (facing == 5) {
                        --var1_z;
                        ++position.x;
                    }

                    var1_y = (int)(position.y = (float)Math.floor((double)var1.y));
                    if (facing == 1) {
                        --var1_y;
                        ++position.y;
                    }

                    var1_z = (int)(position.z = (float)Math.floor((double)var1.z));
                    if (facing == 3) {
                        --var1_z;
                        ++position.z;
                    }



                    //selectionBox
                    //a="tile"
                    Block var23 = this.getBlock(var1_x, var1_y, var1_z);
                    //block is not air and has no liquid
                    if (true/*passed by upper boolean*/) {
                        HitResult var22;
                        //if (var23.a()) {
                            //if ((var22 = var23.a(var1_x, var1_y, var1_z, var1, var2)) != null) {
                                //return var22;
                            //}
                        //} else if ((var22 = var23.a(var1_x, var1_y, var1_z, var1, var2)) != null) {
                           // return var22;
                        //}
                    }
                }

                return null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    @Override
    public Chunk getChunk(long x,long z) {
        return this.chunks.get(new ChunkPos(x,z));
    }

    @Override
    public Block getBlock(long x, long y, long z) {
        return getChunk(x/16,z/16).getBlock((int) (x-x/16*16), (int) y, (int) (z-z/16*16));
    }

    @Override
    public void setBlock(long x, long y, long z, IBlockMaterial material, BlockFacing facing) {
        getChunk(x/16,z/16).setBlock((int) (x-x/16*16), (int) y, (int) (z-z/16*16),material,facing);
    }

    @Override
    public ArrayList<AABB> getCollisionBox(AABB box) {
        return null;
    }

    @Override
    public ArrayList<AABB> getSelectionBox(Entity from) {
        return null;
    }

    @Override
    public int getDimHeight() {
        return this.dimHeight;
    }

    @Override
    public int getLight(long x, long y, long z) {
        return 0;
    }

    public void loadChunks(long cx, long cz, ChunkLoadTicket chunkLoadTicket){

    }

    public void tick(){
        Iterator<Chunk> it=this.chunks.elements.iterator();
        while (it.hasNext()){
            Chunk chunk=it.next();
            chunk.ticket.tickTime();
            if (chunk.ticket.getTime()<=0){
                it.remove();
            }
        }
    }
}
