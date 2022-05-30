#version 450 core
uniform mat4 proj_mat;
vec4[] vertexs={vec4(1,256,1,1)};
void main(void){
    gl_position=vertexs[gl_vertexID]*proj_mat;
}