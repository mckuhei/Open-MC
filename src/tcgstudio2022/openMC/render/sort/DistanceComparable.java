package tcgstudio2022.openMC.render.sort;

import tcgstudio2022.openMC.world.entity.Entity;

public interface DistanceComparable {
    double distanceTo(Entity e);
}
