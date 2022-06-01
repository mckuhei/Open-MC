package tcgstudio2022.openMC.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public class CameraManager {
    public static void setupPerspectiveCamera(float fov,int width,int height){
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        createPerpectiveMatrix(fov, (float) width/height,0.05,131072);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
    }


    public static void setupOrthogonalCamera(int x,int y,int displayWidth,int displayHeight,int w,int h){
        GL11.glViewport(x,y,displayWidth,displayHeight);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(x, w,h, y, -100, 100);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    public static void setupCameraPosition(double x,double y,double z, double xRot,double yRot,double zRot, double rx,double ry,double rz){
        GL11.glTranslated(-rx,-ry,-rz);
        GL11.glRotated(zRot, 0.0f, 0.0f, 1.0f);
        GL11.glRotated(xRot, 1.0f, 0.0f, 0.0f);
        GL11.glRotated(yRot, 0.0f, 1.0f, 0.0f);
        GL11.glTranslated(-x,-y,-z);

    }

    public static void createPerpectiveMatrix(double fov,double aspect,double zn,double zf){
        DoubleBuffer matrix = BufferUtils.createDoubleBuffer(16);
        double radians = fov / 2.0d * 3.1415927d / 180.0d;
        double deltaZ = zf - zn;
        double sine = Math.sin(radians);
        if (deltaZ != 0.0 && sine != 0.0 && aspect != 0.0) {
            double cotangent = Math.cos(radians) / sine;
            double[] IDENTITY_MATRIX = new double[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F};
            int oldPos = matrix.position();
            matrix.put(IDENTITY_MATRIX);
            matrix.position(oldPos);
            matrix.put(0, cotangent / aspect);
            matrix.put(5, cotangent);
            matrix.put(10, -(zf + zn) / deltaZ);
            matrix.put(11, -1.0);
            matrix.put(14, -2.0 * zn * zf / deltaZ);
            matrix.put(15, 0.0);
            GL11.glMultMatrix(matrix);
        }
    }
}
