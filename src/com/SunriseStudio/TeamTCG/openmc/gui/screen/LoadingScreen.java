package com.SunriseStudio.TeamTCG.openmc.gui.screen;

import com.SunriseStudio.TeamTCG.openmc.GameSetting;
import com.SunriseStudio.TeamTCG.openmc.gui.LoadingScreenTask;
import com.SunriseStudio.TeamTCG.openmc.gui.component.Button;
import com.SunriseStudio.TeamTCG.openmc.gui.component.Label;
import com.SunriseStudio.TeamTCG.openmc.gui.component.ProgressBar;
import com.SunriseStudio.TeamTCG.openmc.render.ShapeRenderer;
import com.SunriseStudio.TeamTCG.openmc.render.renderer.FontRenderer;
import com.SunriseStudio.TeamTCG.openmc.resources.textures.TextureManagerDirect;
import org.lwjgl.opengl.Display;
import com.SunriseStudio.TeamTCG.openmc.gui.layout.OriginLayout;

public class LoadingScreen extends Screen{
    public LoadingScreenTask task;
    public ProgressBar progressBar;
    public Screen onFinished;
    public Screen onCancelled;
    public String status;
    public Label label;

    public LoadingScreen(Screen onCancelled,Screen onFinished,LoadingScreenTask task){
        this.onFinished=onFinished;
        this.onCancelled=onCancelled;
        this.task=task;
    }

    @Override
    public void init() {
        this.progressBar=new ProgressBar();
        this.progressBar.setLayout(new OriginLayout(0,0,160,16,OriginLayout.Origin.MIDDLE_MIDDLE,0));
        this.components.add(this.progressBar);
        this.label=new Label("fuck!",26,0xFFFFFF,0, FontRenderer.Alignment.MIDDLE);
        this.label.setLayout(new OriginLayout(0,-40,0,16, OriginLayout.Origin.MIDDLE_MIDDLE,0));
        this.components.add(this.label);
        Button button=new Button(0xFFFFFF,0xFFFFFF,"返回");
        button.setLayout(new OriginLayout(0,24,300,24, OriginLayout.Origin.MIDDLE_MIDDLE,1));
        button.setListener(() -> LoadingScreen.this.task.cancel());
        this.components.add(button);
        new Thread(this.task).start();
    }

    @Override
    public void tick() {
        super.tick();
        this.progressBar.setStatus(this.task.getStatus());
        this.label.text=this.task.getText();
        if(this.task.getStatus()>=100){
            this.minecraft.setScreen(this.onFinished);
        }
        if(this.task.isCancelled()){
            this.minecraft.setScreen(this.onCancelled);
        }
    }

    public void setTask(LoadingScreenTask task){
        this.task=task;
    }

    @Override
    public void render() {
        TextureManagerDirect.bind("/assets/minecraft/textures/gui/bg.png");
        ShapeRenderer.begin();
        ShapeRenderer.drawRectUV(0, Display.getWidth()/ GameSetting.instance.GUIScale,0,Display.getHeight()/GameSetting.instance.GUIScale,-1,-1,0,1,0,1);
        ShapeRenderer.end();
        super.render();
    }
}
