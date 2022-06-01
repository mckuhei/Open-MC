package tcgstudio2022.openMC.world.block;

import tcgstudio2022.openMC.util.phys.AABB;
import tcgstudio2022.openMC.world.block.material.IBlockMaterial;

public class Block {
	int x,y,z;
	byte id;
	public Block(int x,int y,int z,IBlockMaterial iBlockMaterial) {
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

}