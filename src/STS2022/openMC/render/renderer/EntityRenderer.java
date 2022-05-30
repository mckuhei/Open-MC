package tcgstudio2022.openMC.render.renderer;

import tcgstudio2022.openMC.world.entity.Entity;
import tcgstudio2022.openMC.GameSetting;
import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.render.CameraManager;
import tcgstudio2022.openMC.util.LogHandler;
import tcgstudio2022.openMC.world.Level;
import tcgstudio2022.openMC.world.particle.ParticleEngine;

public class EntityRenderer extends IWorldRenderer {
    public ParticleEngine particleEngine;
    public EntityRenderer(Level w, Player p, ParticleEngine pe) {
        super(w, p);
        this.particleEngine=pe;
    }

    @Override
    public void render(float interpolationTime, int displayWidth, int displayHeight) {
        CameraManager.setupPerspectiveCamera(GameSetting.instance.fov, displayWidth, displayHeight);
        CameraManager.setupCameraPosition(this.player.x,this.player.y,this.player.z,this.player.xRot,this.player.yRot,0,0,0,0);
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
