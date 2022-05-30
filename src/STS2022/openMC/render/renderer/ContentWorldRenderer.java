package tcgstudio2022.openMC.render.renderer;

import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.world.LevelListener;
import tcgstudio2022.openMC.world.Level;
import tcgstudio2022.openMC.world.particle.ParticleEngine;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Set;

public class ContentWorldRenderer implements LevelListener {
    public HashMap<String, IWorldRenderer>renderers;
    public Level world;
    public Player player;
    public ParticleEngine particleEngine;

    public ContentWorldRenderer(Level w, Player p, ParticleEngine pe){
        this.world=w;
        this.player=p;
        this.renderers=new HashMap<>();
        this.particleEngine=pe;

        world.addListener(this);

        //register renderers
        this.renderers.put("openMC/renderer:environment",new EnvironmentRenderer(this.world,this.player));
        this.renderers.put("openMC/renderer:chunk",new ChunkRenderer(this.world,this.player));
        this.renderers.put("openMC/renderer:entity",new EntityRenderer(this.world,this.player,this.particleEngine));

    }

    public void render(float interpolationTime, int width, int height) {
        Set<String> keySet=this.renderers.keySet();
        for (String s:keySet){
            GL11.glPushMatrix();
            this.renderers.get(s).render(interpolationTime,width,height);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void tileChanged(long x, int y, long z) {
        Set<String> keySet=this.renderers.keySet();
        for (String s:keySet){
            this.renderers.get(s).chunkUpdate(x/16,y/16,z/16);
        }
    }


}
