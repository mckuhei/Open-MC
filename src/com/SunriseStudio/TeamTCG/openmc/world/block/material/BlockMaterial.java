package com.SunriseStudio.TeamTCG.openmc.world.block.material;

import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;

public class BlockMaterial implements IBlockMaterial{
    int hardness;
    int brakeLevel;
    int[] validFacings;
    AABB[] collisionBoxSizes;
    AABB[] selectionBoxSizes;
    String[] tags;

    public BlockMaterial(AABB[] collisionBoxSizes, AABB[] selectionBoxSizes, int hardness, int brakeLevel, int[] validFacings,String[] tags,IBlockMaterial blockBehavior) {
        this.hardness = hardness;
        this.brakeLevel = brakeLevel;
        this.collisionBoxSizes = collisionBoxSizes;
        this.selectionBoxSizes = selectionBoxSizes;
        this.tags = tags;

        if (blockBehavior != null) {
            if (this.collisionBoxSizes == null) {
                this.collisionBoxSizes = blockBehavior.getCollisionBoxSizes();
                if (this.collisionBoxSizes == null) {
                    this.collisionBoxSizes = new AABB[]{};
                }
            }
            if (this.selectionBoxSizes == null) {
                this.selectionBoxSizes = blockBehavior.getSelectionBoxSizes();
                if (this.selectionBoxSizes == null) {
                    this.selectionBoxSizes = new AABB[]{};
                }
            }
            if (this.hardness == -1) {
                this.hardness = blockBehavior.getHardNess();
                if (this.hardness == -1) {
                    this.hardness = 1;
                }
            }
            if (this.brakeLevel == -1) {
                this.brakeLevel = blockBehavior.getBrakeLevel();
                if (this.brakeLevel == -1) {
                    this.brakeLevel = 1;
                }
            }
            if (this.tags == null) {
                this.tags = blockBehavior.getTags();
                if (this.tags == null) {
                    this.tags = new String[0];
                }
            }
            if (this.validFacings == null) {
                this.validFacings = blockBehavior.getEnabledFacings();
                if (this.validFacings == null) {
                    this.validFacings = new int[0];
                }
            }
        }
    }




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

    @Override
    public String[] getTags() {
        return this.tags;
    }
}
