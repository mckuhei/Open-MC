package com.SunriseStudio.TeamTCG.openmc.render.sort;

import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;

public interface DistanceComparable {
    double distanceTo(Entity e);
}
