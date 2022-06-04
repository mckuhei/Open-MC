package com.SunriseStudio.TeamTCG.openmc.net;

import com.SunriseStudio.TeamTCG.openmc.world.block.Block;
import com.SunriseStudio.TeamTCG.openmc.world.chunk.Chunk;

public interface INetClientConnecter {
    Chunk getChunk(long cx, long cy, long cz);
    void PlaceBlock(long x, long y, long z, Block block);
    void destroyBlock(long x,long y,long z,Block oldBlock);

    String sendMessage(String message);

    void getServerInfo(String ip);
    void joinServer(String ip);
    void leaveServer(String ip);
}
