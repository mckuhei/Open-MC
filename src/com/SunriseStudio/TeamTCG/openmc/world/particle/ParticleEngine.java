package com.SunriseStudio.TeamTCG.openmc.world.particle;

import com.SunriseStudio.TeamTCG.openmc.render.vertex.VertexArrayBuilder;
import com.SunriseStudio.TeamTCG.openmc.render.vertex.VertexArrayUploader;
import com.SunriseStudio.TeamTCG.openmc.resources.textures.Textures;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;
import com.SunriseStudio.TeamTCG.openmc.world.Level;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class ParticleEngine {
    protected Level world;
    private List<Particle> particles = new ArrayList<Particle>();
    private Textures textures;

    public ParticleEngine(Level world, Textures textures) {
        this.world = world;
        this.textures = textures;
    }

    public void add(Particle p) {
        this.particles.add(p);
    }

    public void tick() {
        for (int i = 0; i < this.particles.size(); ++i) {
            Particle p = this.particles.get(i);
            p.tick();
            if (!p.removed) continue;
            this.particles.remove(i--);
        }
    }

    public void render(Entity player, float a, int layer) {
        if (this.particles.size() == 0) {
            return;
        }
        GL11.glEnable(3553);
        int id = this.textures.loadTexture("/assets/terrain.png", 9728);
        GL11.glBindTexture(3553, id);
        float xa = -((float)Math.cos((double)player.yRot * Math.PI / 180.0));
        float za = -((float)Math.sin((double)player.yRot * Math.PI / 180.0));
        float xa2 = -za * (float)Math.sin((double)player.xRot * Math.PI / 180.0);
        float za2 = xa * (float)Math.sin((double)player.xRot * Math.PI / 180.0);
        float ya = (float)Math.cos((double)player.xRot * Math.PI / 180.0);
        VertexArrayBuilder t = new VertexArrayBuilder(16);
        GL11.glColor4f(0.8f, 0.8f, 0.8f, 1.0f);
        t.begin();
        for (int i = 0; i < this.particles.size(); ++i) {
            Particle p = this.particles.get(i);
            if (p.isLit() == (layer == 1)) continue;
            p.render(t, a, xa, ya, za, xa2, za2);
        }
        t.end();
        VertexArrayUploader.upload(t);
        GL11.glDisable(3553);
    }
}
