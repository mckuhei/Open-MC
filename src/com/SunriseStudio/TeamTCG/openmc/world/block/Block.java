package com.SunriseStudio.TeamTCG.openmc.world.block;

import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.IBlockMaterial;

public class Block {
	final long x,y,z;
	BlockFacing facing;
	public Block(long x,long y,long z,IBlockMaterial iBlockMaterial) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.material=iBlockMaterial;
	}

	private IBlockMaterial material;

	public IBlockMaterial getMaterial() {
		return this.material;
	}

	public void setMaterial(IBlockMaterial material) {
		this.material = material;
	}

	public AABB[] getCollisionBox(){
		return this.material.getCollisionBox(this.x,this.y,this.z);
	}

	public String[] getTags() {
		return this.material.getTags();
	}

	public void setFacing(BlockFacing f) {
		this.facing=f;
	}

	public BlockFacing getFacing() {
		return facing;
	}
}