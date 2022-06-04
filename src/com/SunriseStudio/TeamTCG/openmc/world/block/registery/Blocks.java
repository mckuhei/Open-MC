package com.SunriseStudio.TeamTCG.openmc.world.block.registery;

import com.SunriseStudio.TeamTCG.openmc.util.collections.ArrayUtil;
import com.SunriseStudio.TeamTCG.openmc.world.block.BlockFacing;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.BlockMaterial;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.IBlockMaterial;
import com.SunriseStudio.TeamTCG.openmc.world.dim.IDimension;
import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;

@BlockRegisterer(namespace="minecraft/vanilla")
public class Blocks {
    @BlockGetter(id = "dir",behavior = "null")
    public IBlockMaterial air(IBlockMaterial blockBehavior) {
        return new BlockMaterial(new AABB[0],new AABB[0],0,0,new int[0],new String[]{"transparent"},blockBehavior);
    }

    @BlockGetter(id = "dirt",behavior = "block")
    public IBlockMaterial dirt(IBlockMaterial blockBehavior) {
        return new BlockMaterial(null,null,0,0,null,null,blockBehavior){
            @Override
            public void onBlockRandomTick(IDimension dimension, long x, long y, long z) {
                if(dimension.getLight(x,y+1,z)>80&& ArrayUtil.arrayMatchAny("minecraft/vanilla:solid",dimension.getBlock(x,y+1,z).getTags())){
                    dimension.setBlock(x,y,z,BlockMap.getByID("minecraft/vanilla:grass_block"), BlockFacing.Up);
                }
            }
        };
    }

    @BlockGetter(id = "grass_block",behavior = "block")
    public IBlockMaterial grassBlock(IBlockMaterial blockBehavior) {
        return new BlockMaterial(null,null,0,0,null,null,blockBehavior){
            @Override
            public void onBlockRandomTick(IDimension dimension, long x, long y, long z) {
                if(dimension.getLight(x,y+1,z)>80&& ArrayUtil.arrayMatchAny("minecraft/vanilla:solid",dimension.getBlock(x,y+1,z).getTags())){
                    dimension.setBlock(x,y,z,BlockMap.getByID("minecraft/vanilla:dirt"), BlockFacing.Up);
                }
            }
        };
    }

    @BlockGetter(id = "stone",behavior = "stone")
    public IBlockMaterial stone(IBlockMaterial blockBehavior) {
        return new BlockMaterial(null,null,0,0,null,null,blockBehavior);
    }

    @BlockGetter(id = "bedrock",behavior = "stone")
    public IBlockMaterial bedrock(IBlockMaterial blockBehavior) {
        return new BlockMaterial(null,new AABB[0],114514,114514,null,null,blockBehavior);
    }
}
