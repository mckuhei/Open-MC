package tcgstudio2022.openMC.render.renderer;

import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.world.Level;

public abstract class IWorldRenderer {
    public Player player;
    public Level world;
    public abstract void render(float interpolationTime, int displayWidth, int displayHeight);
    public abstract void chunkUpdate(long x,long y,long z);
    public IWorldRenderer(Level w, Player p){
        this.world=w;
        this.player=p;
    }


}
