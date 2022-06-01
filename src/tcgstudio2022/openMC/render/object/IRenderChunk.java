package tcgstudio2022.openMC.render.object;

import tcgstudio2022.openMC.render.sort.DistanceComparable;
import tcgstudio2022.openMC.render.vertex.VertexArrayBuilder;
import tcgstudio2022.openMC.render.vertex.VertexBuildable;
import tcgstudio2022.openMC.util.phys.AABB;

public interface IRenderChunk extends DistanceComparable {
    void render(double camX,double camY,double camZ);
    void update();
    AABB getVisibleArea();
    VertexArrayBuilder getChunkBuilder();
    int getRenderList();
}
