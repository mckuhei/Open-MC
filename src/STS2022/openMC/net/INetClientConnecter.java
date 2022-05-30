package tcgstudio2022.openMC.net;

import tcgstudio2022.openMC.world.Chunk;
import tcgstudio2022.openMC.world.block.Block;

public interface INetClientConnecter {
    Chunk getChunk(long cx, long cy, long cz);
    void PlaceBlock(long x,long y,long z,Block block);
    void destroyBlock(long x,long y,long z,Block oldBlock);

    String sendMessage(String message);

    void getServerInfo(String ip);
    void joinServer(String ip);
    void leaveServer(String ip);
}
