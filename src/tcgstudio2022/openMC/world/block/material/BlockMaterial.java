package tcgstudio2022.openMC.world.block.material;

import tcgstudio2022.openMC.util.phys.AABB;

public class BlockMaterial implements IBlockMaterial{
    int hardness;
    int brakeLevel;
    int[] validFacings;
    AABB[] collisionBoxSizes;
    AABB[] selectionBoxSizes;


    @Override
    public int getHardNess() {
        return this.hardness;
    }

    @Override
    public int getBrakeLevel() {
        return this.brakeLevel;
    }

    @Override
    public int[] getEnabledFacings() {
        return this.validFacings;
    }

    @Override
    public AABB[] getCollisionBoxSizes() {
        return this.collisionBoxSizes;
    }

    @Override
    public AABB[] getSelectionBoxSizes() {
        return this.selectionBoxSizes;
    }



}
