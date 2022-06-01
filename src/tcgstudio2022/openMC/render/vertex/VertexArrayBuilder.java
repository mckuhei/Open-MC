package tcgstudio2022.openMC.render.vertex;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class VertexArrayBuilder implements VertexBuildable{
    //data
    public FloatBuffer buffer;
    private final float[] array;
    public int vertices = 0;

    //texture
    private float u;
    private float v;
    private float layer;

    //color
    private float r=1.0f;
    private float g=1.0f;
    private float b=1.0f;
    private float a=1.0f;

    //normal
    private float n=1.0f;
    private float f=1.0f;
    private float l=1.0f;

    private int p = 0;

    public VertexArrayBuilder(int size) {
        this.array=new float[size];
        this.buffer=BufferUtils.createFloatBuffer(size);
    }

    public void end() {
        if (this.vertices > 0) {
            this.buffer.clear();
            this.buffer.put(this.array, 0, this.p);
            this.buffer.flip();
        }
    }

    public void clear() {
        this.vertices = 0;
        this.buffer.clear();
        this.p = 0;
    }

    public void begin() {
        this.clear();
    }

    //color
    public void color(int c) {
        byte r = (byte) (c >> 16);
        byte g = (byte) (c >> 8);
        byte b = (byte) c;
        this.colorB(r, g, b, (byte) 255);
    }

    public void colorB(byte r, byte g, byte b) {
        float r2 = (float)(r&0xFF) / 255.0f;
        float g2 = (float)(g&0xFF) / 255.0f;
        float b2 = (float)(b&0xFF) / 255.0f;
        this.color(r2,g2,b2,1.0f);
    }

    public void colorB(byte r,byte g,byte b,byte a){
        float r2 = (float)(r&0xFF) / 255.0f;
        float g2 = (float)(g&0xFF) / 255.0f;
        float b2 = (float)(b&0xFF) / 255.0f;
        float a2 = (float)(a&0xFF) / 255.0f;
        this.color(r2,g2,b2,a2);
    }

    public final void color(float r, float g, float b,float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    //texture
    public void vertexUV(double x, double y, double z, float u, float v) {
        this.vertexUV(x,y,z,u,v,0.0f);
    }

    public void vertexUV(double x, double y, double z, float u, float v,float layer) {
        this.tex(u, v,layer);
        this.vertex( x,  y,  z);
    }

    public void tex(float u, float v,float layer) {
        this.u = u;
        this.v = v;
        this.layer=layer;
    }

    public void vertex(double x, double y, double z) {
        //"t4f"
        this.array[this.p++] = this.u;
        this.array[this.p++] = this.v;
        this.array[this.p++] = this.layer;
        this.array[this.p++] = 1f;

        //"c4f"
        this.array[this.p++] = this.r;
        this.array[this.p++] = this.g;
        this.array[this.p++] = this.b;
        this.array[this.p++] = this.a;

        //"n3f"
        this.array[this.p++] = this.n;
        this.array[this.p++] = this.f;
        this.array[this.p++] = this.l;

        //"v4f"
        this.array[this.p++] = (float)x;
        this.array[this.p++] = (float)y;
        this.array[this.p++] = (float)z;
        this.array[this.p++] = 1.0f;
        ++this.vertices;
    }

    @Override
    public void normal(double n, double f, double l) {
        this.n=(float)n;
        this.f=(float)f;
        this.l=(float)l;
    }

    public int getVertices() {
        return vertices;
    }
}
