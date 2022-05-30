package tcgstudio2022.openMC.util.phys;

import tcgstudio2022.openMC.world.entity.Player;

public class HitResult {
    public int type;
    public int x;
    public int y;
    public int z;
    public int f;

    public HitResult(int type, int x, int y, int z, int f) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.f = f;
    }

    public boolean isCloserThan(Player player, HitResult o, int editMode) {
        double dist2;
        double dist = this.distanceTo(player, 0);
        if (dist < (dist2 = o.distanceTo(player, 0))) {
            return true;
        }
        dist = this.distanceTo(player, editMode);
        return dist < (dist2 = o.distanceTo(player, editMode));
    }

    private double distanceTo(Player player, int editMode) {
        int xx = this.x;
        int yy = this.y;
        int zz = this.z;
        if (editMode == 1) {
            if (this.f == 0) {
                --yy;
            }
            if (this.f == 1) {
                ++yy;
            }
            if (this.f == 2) {
                --zz;
            }
            if (this.f == 3) {
                ++zz;
            }
            if (this.f == 4) {
                --xx;
            }
            if (this.f == 5) {
                ++xx;
            }
        }
        double xd = (float)xx - player.x;
        double yd = (float)yy - player.y;
        double zd = (float)zz - player.z;
        return xd * xd + yd * yd + zd * zd;
    }
}
