package tcgstudio2022.openMC.world.dim;

import tcgstudio2022.openMC.world.entity.Entity;
import tcgstudio2022.openMC.util.phys.AABB;
import tcgstudio2022.openMC.world.Chunk;
import tcgstudio2022.openMC.world.block.Block;

import java.util.ArrayList;

public interface IDimension {
    Chunk getChunk(long x, long y, long z);
    Block getBlock(long x, long y, long z);
    ArrayList<AABB> getCollisionBox(AABB box);
    ArrayList<AABB> getSelectionBox(Entity from);
    int getDimHeight();
}
