package com.SunriseStudio.TeamTCG.openmc.render.object;

import com.SunriseStudio.TeamTCG.openmc.util.collections.UUID;
import com.SunriseStudio.TeamTCG.openmc.world.chunk.ChunkPos;

public record RenderChunkPos(long x, long y, long z) implements UUID<RenderChunkPos> {
    @Override
    public boolean compare(RenderChunkPos another){
        return this.x==another.x&&this.y==another.y&&this.z==another.z;
    }
}
