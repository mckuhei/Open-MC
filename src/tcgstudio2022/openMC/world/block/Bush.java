package tcgstudio2022.openMC.world.block;

import tcgstudio2022.openMC.render.vertex.VertexBuildable;
import tcgstudio2022.openMC.world.Level;
import tcgstudio2022.openMC.util.phys.AABB;

import java.util.Random;

public class Bush
extends Tile {
    protected Bush(int id) {
        super(id);
        this.tex = 15;
        this.setTicking(true);
    }

    @Override
    public void tick(Level world, int x, int y, int z, Random random) {
        int below = world.getTile(x, y - 1, z);
        if (!world.isLit(x, y, z) || below != dirt.id && below != grass.id) {
            world.setTile(x, y, z, 0);
        }
    }

    @Override
    public void render(VertexBuildable vertexBuilder, Level world, int layer, long x, int y, long z, long ax, int ay, long az) {
        if (world.isLit(x, y, z) ^ layer != 1) {
            return;
        }
        int tex = this.getTexture(15);
        float u0 = (float)(tex % 16) / 16.0f;
        float u1 = u0 + 0.0624375f;
        float v0 = (float)(tex / 16) / 16.0f;
        float v1 = v0 + 0.0624375f;
        int rots = 2;
        vertexBuilder.colorB((byte)255, (byte)255, (byte)255);
        for (int r = 0; r < rots; ++r) {
            float xa = (float)(Math.sin((double)r * Math.PI / (double)rots + 0.7853981633974483) * 0.5);
            float za = (float)(Math.cos((double)r * Math.PI / (double)rots + 0.7853981633974483) * 0.5);
            float x0 = (float)x + 0.5f - xa;
            float x1 = (float)x + 0.5f + xa;
            float y0 = (float)y + 0.0f;
            float y1 = (float)y + 1.0f;
            float z0 = (float)z + 0.5f - za;
            float z1 = (float)z + 0.5f + za;
            vertexBuilder.vertexUV(x0, y1, z0, u1, v0);
            vertexBuilder.vertexUV(x1, y1, z1, u0, v0);
            vertexBuilder.vertexUV(x1, y0, z1, u0, v1);
            vertexBuilder.vertexUV(x0, y0, z0, u1, v1);
            vertexBuilder.vertexUV(x1, y1, z1, u1, v0);
            vertexBuilder.vertexUV(x0, y1, z0, u0, v0);
            vertexBuilder.vertexUV(x0, y0, z0, u0, v1);
            vertexBuilder.vertexUV(x1, y0, z1, u1, v1);
        }
    }

    @Override
    public AABB getAABB(long x, int y, long z) {
        return null;
    }

    @Override
    public boolean blocksLight() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
