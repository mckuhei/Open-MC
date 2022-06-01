package tcgstudio2022.openMC.world.worldGen;

import tcgstudio2022.openMC.world._Chunk;
import tcgstudio2022.openMC.world.Level;

public class WorldGen {
    public Level world;
    public long seed;
    public ChunkProvider chunkProvider;

    public WorldGen(Level w, long seed){
        this.world=w;
        this.seed=seed;
        this.chunkProvider=new ChunkProvider(this.world,this.seed);
    }

    public _Chunk generateChunk(long cx, long cz) {
        return this.chunkProvider.getChunk(cx, cz);
    }
}
