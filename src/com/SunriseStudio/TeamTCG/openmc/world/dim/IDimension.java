package com.SunriseStudio.TeamTCG.openmc.world.dim;

import com.SunriseStudio.TeamTCG.openmc.world.block.Block;
import com.SunriseStudio.TeamTCG.openmc.world.block.BlockFacing;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.IBlockMaterial;
import com.SunriseStudio.TeamTCG.openmc.world.chunk.Chunk;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;
import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;

import java.util.ArrayList;

public interface IDimension {
    Chunk getChunk(long x, long z);
    Block getBlock(long x, long y, long z);
    void setBlock(long x, long y, long z, IBlockMaterial material, BlockFacing facing);
    ArrayList<AABB> getCollisionBox(AABB box);
    ArrayList<AABB> getSelectionBox(Entity from);
    int getDimHeight();
    int getLight(long x, long y, long z);
}
