//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tcgstudio2022.openMC.world.dim;



public final class Vec3f {
    public float x;
    public float y;
    public float z;

    public Vec3f(float var1, float var2, float var3) {
        this.x = var1;
        this.y = var2;
        this.z = var3;
    }

    public final Vec3f a(Vec3f var1) {
        return new Vec3f(this.x - var1.x, this.y - var1.y, this.z - var1.z);
    }

    public final Vec3f a() {
        return null;
    }

    public final Vec3f a(float var1, float var2, float var3) {
        return new Vec3f(this.x + var1, this.y + var2, this.z + var3);
    }

    public final float b(Vec3f var1) {
        float var2 = var1.x - this.x;
        float var3 = var1.y - this.y;
        float var4 = var1.z - this.z;
        return 0;
    }

    public final float c(Vec3f var1) {
        float var2 = var1.x - this.x;
        float var3 = var1.y - this.y;
        float var4 = var1.z - this.z;
        return var2 * var2 + var3 * var3 + var4 * var4;
    }

    public final Vec3f a(Vec3f var1, float var2) {
        float var3 = var1.x - this.x;
        float var4 = var1.y - this.y;
        float var5 = var1.z - this.z;
        if (var3 * var3 < 1.0E-7F) {
            return null;
        } else {
            return !((var2 = (var2 - this.x) / var3) < 0.0F) && !(var2 > 1.0F) ? new Vec3f(this.x + var3 * var2, this.y + var4 * var2, this.z + var5 * var2) : null;
        }
    }

    public final Vec3f b(Vec3f var1, float var2) {
        float var3 = var1.x - this.x;
        float var4 = var1.y - this.y;
        float var5 = var1.z - this.z;
        if (var4 * var4 < 1.0E-7F) {
            return null;
        } else {
            return !((var2 = (var2 - this.y) / var4) < 0.0F) && !(var2 > 1.0F) ? new Vec3f(this.x + var3 * var2, this.y + var4 * var2, this.z + var5 * var2) : null;
        }
    }

    public final Vec3f c(Vec3f var1, float var2) {
        float var3 = var1.x - this.x;
        float var4 = var1.y - this.y;
        float var5;
        if ((var5 = var1.z - this.z) * var5 < 1.0E-7F) {
            return null;
        } else {
            return !((var2 = (var2 - this.z) / var5) < 0.0F) && !(var2 > 1.0F) ? new Vec3f(this.x + var3 * var2, this.y + var4 * var2, this.z + var5 * var2) : null;
        }
    }

    public final String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
