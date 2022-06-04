package com.SunriseStudio.TeamTCG.openmc.render.renderer;

import com.SunriseStudio.TeamTCG.openmc.GameSetting;
import com.SunriseStudio.TeamTCG.openmc.util.GLUtil;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Player;
import com.SunriseStudio.TeamTCG.openmc.util.LogHandler;
import com.SunriseStudio.TeamTCG.openmc.world.Level;
import com.SunriseStudio.TeamTCG.openmc.world.particle.ParticleEngine;

public class EntityRenderer extends IWorldRenderer {
    public ParticleEngine particleEngine;
    public EntityRenderer(Level w, Player p, ParticleEngine pe) {
        super(w, p);
        this.particleEngine=pe;
    }

    @Override
    public void render(float interpolationTime, int displayWidth, int displayHeight) {
        GLUtil.setupPerspectiveCamera(GameSetting.instance.fov, displayWidth, displayHeight);
        GLUtil.setupCameraPosition(this.player.x,this.player.y,this.player.z,this.player.xRot,this.player.yRot,0,0,0,0);
        for (Entity e:this.world.entities){
            e.render(interpolationTime);
        }
        this.particleEngine.render(this.player,interpolationTime,0);
        LogHandler.checkGLError("render entity");
    }

    @Override
    public void chunkUpdate(long x, long y, long z) {

    }
}
