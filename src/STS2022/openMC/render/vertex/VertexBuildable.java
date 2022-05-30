package tcgstudio2022.openMC.render.vertex;

public interface VertexBuildable {
    void end() ;

    void begin();

    //color
    void color(int c) ;

    void colorB(byte r, byte g, byte b);

    void colorB(byte r, byte g, byte b, byte a);

    void color(float r, float g, float b,float a);

    //texture
    void vertexUV(double x, double y, double z, float u, float v);

    void vertexUV(double x, double y, double z, float u, float v,float layer);

    void tex(float u, float v,float layer);

    void vertex(double x, double y, double z);

    void normal(double n,double f,double l);

    int getVertices();
}
