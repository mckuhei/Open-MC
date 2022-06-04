package com.SunriseStudio.TeamTCG.openmc.render.object;

import com.SunriseStudio.TeamTCG.openmc.render.sort.DistanceComparable;
import com.SunriseStudio.TeamTCG.openmc.render.vertex.VertexArrayBuilder;
import com.SunriseStudio.TeamTCG.openmc.util.math.AABB;

public interface IRenderChunk extends DistanceComparable {
    void render(double camX,double camY,double camZ);
    void update();
    AABB getVisibleArea();
    VertexArrayBuilder getChunkBuilder();
    int getRenderList();
}
