package com.SunriseStudio.TeamTCG.openmc.render.renderer;

import com.SunriseStudio.TeamTCG.openmc.world.entity.Player;
import com.SunriseStudio.TeamTCG.openmc.world.Level;

import java.util.concurrent.ExecutionException;

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
