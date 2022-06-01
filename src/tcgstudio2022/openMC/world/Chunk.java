package tcgstudio2022.openMC.world;

import tcgstudio2022.openMC.world.block.Block;
import tcgstudio2022.openMC.world.dim.IDimension;

public class Chunk {
    private long x,z;
    public static final int WIDTH=16;
    private Block[][][] blocks;
    public IDimension dimension;

    public Chunk(IDimension dimension,long x,long z){
        this.blocks=new Block[WIDTH][dimension.getDimHeight()][WIDTH];
        this.x=x;
        this.z=z;
    }

}
