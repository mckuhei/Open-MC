package com.SunriseStudio.TeamTCG.openmc.world.block.material;

import com.SunriseStudio.TeamTCG.openmc.util.collections.ArrayUtil;
import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;
import com.SunriseStudio.TeamTCG.openmc.world.dim.IDimension;

public interface IBlockMaterial {
    int getHardNess();
    int getBrakeLevel();
    int[] getEnabledFacings();

    AABB[] getCollisionBoxSizes();
    AABB[] getSelectionBoxSizes();


    default AABB[] getCollisionBox(long x, long y, long z){
        AABB[] aabbs=getCollisionBoxSizes().clone();
        ArrayUtil.iterateArray(aabbs, item -> item.move(x,y,z));
        return aabbs;
    }

    default AABB[] getSelectionBox(long x, long y, long z){
        AABB[] aabbs=getCollisionBoxSizes().clone();
        ArrayUtil.iterateArray(aabbs, item -> item.move(x,y,z));
        return aabbs;
    }

    default void onBlockUpdate(IDimension dimension,long x,long y,long z){
        //do nth
    };

    default void onBlockRandomTick(IDimension dimension,long x,long y,long z){
        //do nth
    }

    String[] getTags();
}
