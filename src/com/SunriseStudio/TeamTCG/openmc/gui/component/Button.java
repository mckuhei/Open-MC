package com.SunriseStudio.TeamTCG.openmc.gui.component;


import com.SunriseStudio.TeamTCG.openmc.render.ShapeRenderer;
import com.SunriseStudio.TeamTCG.openmc.render.renderer.FontRenderer;
import com.SunriseStudio.TeamTCG.openmc.resources.textures.TextureManagerDirect;
import org.lwjgl.input.Mouse;

public class Button extends Component{
    public static final int HEIGHT=20;
    public String text;
    public int textColor;
    public int color;
    private Listener listener;

    public Button(int color, int textColor, String text) {
        this.color=color;
        this.textColor=textColor;
        this.text=text;
    }
    public boolean enabled=true;
    public boolean hovered=false;

    private void render(int x, int y, int w, int h,int layer) {
        FontRenderer.render(text, x+w/2,y+8,textColor,8, FontRenderer.Alignment.MIDDLE);
        drawBack(x,y,-1,w,h,0xFFFFFF);
    }

    private void drawBack(double x,double y,double z,int w,int h,int col){
        String path="/button";
        if(this.hovered){
            path="/button_selected";
        }
        if(!this.enabled){
            path="/button_disabled";
        }

        TextureManagerDirect.bind("/assets/minecraft/textures/gui"+path+".png");
        ShapeRenderer.setColor(col);
        ShapeRenderer.begin();
        ShapeRenderer.drawRectUV(x,x+HEIGHT,y,y+h,z,z,0,0.1,0,1);
        ShapeRenderer.drawRectUV(x+HEIGHT,x+w-HEIGHT,y,y+h,z,z,0.1,0.9,0,1);
        ShapeRenderer.drawRectUV(x+w-HEIGHT,x+w,y,y+h,z,z,0.9,1,0,1);
        ShapeRenderer.end();
    }



    @Override
    public void tick(int xm,int ym) {
        int x0=this.layoutManager.ax;
        int x1=x0+this.layoutManager.aWidth;
        int y0=this.layoutManager.ay;
        int y1=y0+this.layoutManager.aHeight;
        if(xm>x0&&xm<x1&&ym>y0&&ym<y1){
            this.hovered=true;
            if(Mouse.isButtonDown(0)){
                this.listener.buttonClicked();
            }
        }else{
            this.hovered=false;
        }
    }

    @Override
    public void render() {
        this.render(this.layoutManager.ax,this.layoutManager.ay,this.layoutManager.aWidth,this.layoutManager.aHeight,this.layer);
    }

    public void setListener(Listener listener) {
        this.listener=listener;
        
    }

    public interface Listener {
        void buttonClicked();
    }
}
