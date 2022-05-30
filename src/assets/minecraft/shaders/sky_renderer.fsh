#version 450 core
out vec4 color;
uniform vec4 skyColor;
void main(void){
    color =fog(skyColor);
}