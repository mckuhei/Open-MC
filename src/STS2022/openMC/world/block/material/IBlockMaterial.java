package tcgstudio2022.openMC.world.block.material;

import tcgstudio2022.openMC.util.CollectionUtil;
import tcgstudio2022.openMC.util.phys.AABB;

public interface IBlockMaterial {
    int getHardNess();
    int getBrakeLevel();
    int[] getEnabledFacings();

    AABB[] getCollisionBoxSizes();
    AABB[] getSelectionBoxSizes();


    default AABB[] getCollisionBox(long x, long y, long z){
        AABB[] aabbs=getCollisionBoxSizes().clone();
        CollectionUtil.iterateArray(aabbs, item -> item.move(x,y,z));
        return aabbs;
    }

    default AABB[] getSelectionBox(long x, long y, long z){
        AABB[] aabbs=getCollisionBoxSizes().clone();
        CollectionUtil.iterateArray(aabbs, item -> item.move(x,y,z));
        return aabbs;
    }
}
