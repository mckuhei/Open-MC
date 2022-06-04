package com.SunriseStudio.TeamTCG.openmc.world.chunk;

import com.SunriseStudio.TeamTCG.openmc.util.collections.UUID;

public record ChunkPos (long x, long z)implements UUID<ChunkPos> {
    @Override
    public boolean compare(ChunkPos another){
        return this.x==another.x&&this.z==another.z;
    }
}
