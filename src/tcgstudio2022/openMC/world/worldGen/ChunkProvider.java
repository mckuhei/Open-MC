package tcgstudio2022.openMC.world.worldGen;

import tcgstudio2022.openMC.world._Chunk;
import tcgstudio2022.openMC.world.Level;
import tcgstudio2022.openMC.world.biome.Biome;
import tcgstudio2022.openMC.world.block.Tile;
import tcgstudio2022.openMC.world.worldGen.synth.PerlinNoise;

import java.util.Random;

public class ChunkProvider{
    public long seed;
    public Level world;
    public PerlinNoise noise;
    public long seaLevel=128;
    public double noise_scale=32;
    public int verticle_scale=4;
    public ChunkProvider(Level world,long seed) {
        this.world=world;
        this.seed=seed;
        this.noise=new PerlinNoise(new Random(seed),verticle_scale);
    }

    public _Chunk getChunk(long cx, long cz){
        Biome[][] biomesMap=new Biome[16][16];
        double[][] heightMap=new double[16][16];
        _Chunk c=new _Chunk(cx,cz,world);
        for (int i = 0; i < _Chunk.W; i++) {
            for (int k = 0; k < _Chunk.W; k++) {
                biomesMap[i][k]=Biome.getDefault();
                heightMap[i][k] = seaLevel +noise.getValue((cx*16+i)/noise_scale,(cz*16+k)/noise_scale)*biomesMap[i][k].contrast+biomesMap[i][k].height;
                for (int j = 1; j < seaLevel; j++) {
                        c.setBlock(i,j,k,(byte) Tile.water.id);
                    }//add water
                for (int j = 0; j < heightMap[i][k]; j++) {
                    c.setBlock(i,j,k,(byte) Tile.rock.id);
                }
                for (int j = 0; j<biomesMap[i][k].coverBlock.length; j++) {
                    c.setBlock(i,(int) (j + heightMap[i][k]),k,(byte) biomesMap[i][k].coverBlock[j]);
                }
                c.setBlock(i,0,k, (byte) Tile.bedrock.id);
            }
        }
        return c;
    }
}
