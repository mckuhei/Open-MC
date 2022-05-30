package tcgstudio2022.openMC.util.phys;

public class AABB {
    private double epsilon = 0.0f;
    public double x0;
    public double y0;
    public double z0;
    public double x1;
    public double y1;
    public double z1;

    public AABB(double x0, double y0, double z0, double x1, double y1, double z1) {
        this.x0 = x0;
        this.y0 = y0;
        this.z0 = z0;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
    }

    public AABB expand(double xa, double ya, double za) {
        double _x0 = this.x0;
        double _y0 = this.y0;
        double _z0 = this.z0;
        double _x1 = this.x1;
        double _y1 = this.y1;
        double _z1 = this.z1;
        return new AABB(_x0, _y0, _z0, _x1, _y1, _z1);
    }

    public AABB grow(double xa, double ya, double za) {
        double _x0 = this.x0 - xa;
        double _y0 = this.y0 - ya;
        double _z0 = this.z0 - za;
        double _x1 = this.x1 + xa;
        double _y1 = this.y1 + ya;
        double _z1 = this.z1 + za;
        return new AABB(_x0, _y0, _z0, _x1, _y1, _z1);
    }

    public AABB cloneMove(double xa, double ya, double za) {
        return new AABB(this.x0 + za, this.y0 + ya, this.z0 + za, this.x1 + xa, this.y1 + ya, this.z1 + za);
    }

    public double clipXCollide(AABB c, double xa) {
        double max;
        if (c.y1 <= this.y0 || c.y0 >= this.y1) {
            return xa;
        }
        if (c.z1 <= this.z0 || c.z0 >= this.z1) {
            return xa;
        }
        if (xa > 0.0f && c.x1 <= this.x0 && (max = this.x0 - c.x1 - this.epsilon) < xa) {
            xa = max;
        }
        if (xa < 0.0f && c.x0 >= this.x1 && (max = this.x1 - c.x0 + this.epsilon) > xa) {
            xa = max;
        }
        return xa;
    }

    public double clipYCollide(AABB c, double ya) {
        double max;
        if (c.x1 <= this.x0 || c.x0 >= this.x1) {
            return ya;
        }
        if (c.z1 <= this.z0 || c.z0 >= this.z1) {
            return ya;
        }
        if (ya > 0.0f && c.y1 <= this.y0 && (max = this.y0 - c.y1 - this.epsilon) < ya) {
            ya = max;
        }
        if (ya < 0.0f && c.y0 >= this.y1 && (max = this.y1 - c.y0 + this.epsilon) > ya) {
            ya = max;
        }
        return ya;
    }

    public double clipZCollide(AABB c, double za) {
        double max;
        if (c.x1 <= this.x0 || c.x0 >= this.x1) {
            return za;
        }
        if (c.y1 <= this.y0 || c.y0 >= this.y1) {
            return za;
        }
        if (za > 0.0f && c.z1 <= this.z0 && (max = this.z0 - c.z1 - this.epsilon) < za) {
            za = max;
        }
        if (za < 0.0f && c.z0 >= this.z1 && (max = this.z1 - c.z0 + this.epsilon) > za) {
            za = max;
        }
        return za;
    }

    public boolean intersects(AABB c) {
        if (c.x1 <= this.x0 || c.x0 >= this.x1) {
            return false;
        }
        if (c.y1 <= this.y0 || c.y0 >= this.y1) {
            return false;
        }
        return !(c.z1 <= this.z0) && !(c.z0 >= this.z1);
    }

    public void move(double xa, double ya, double za) {
        this.x0 += xa;
        this.y0 += ya;
        this.z0 += za;
        this.x1 += xa;
        this.y1 += ya;
        this.z1 += za;
    }

    public AABB grow(double r) {
        return this.grow(r,r,r);
    }
}
