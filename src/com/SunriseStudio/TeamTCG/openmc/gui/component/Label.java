package com.SunriseStudio.TeamTCG.openmc.gui.component;


import com.SunriseStudio.TeamTCG.openmc.render.renderer.FontRenderer;

public class Label extends Component {
    public String text;
    public int size;
    public int color;

    private FontRenderer.Alignment alignment;
    public Label(String text, int size, int color, int layer, FontRenderer.Alignment alignment) {
        this.text=text;
        this.color=color;
        this.size=size;
        this.alignment=alignment;
    }


    @Override
    public void render() {
        FontRenderer.render(text,layoutManager.ax,layoutManager.ay,color,layoutManager.height, this.alignment);
    }

}
