package com.SunriseStudio.TeamTCG.openmc.gui.screen;

import com.SunriseStudio.TeamTCG.openmc.GameSetting;
import com.SunriseStudio.TeamTCG.openmc.Minecraft;
import com.SunriseStudio.TeamTCG.openmc.gui.component.Component;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;

public abstract class Screen {
    protected Minecraft minecraft;
    public int width;
    public int height;
    protected ArrayList<Component> components=new ArrayList<>();

    //init
    public Screen(){
        this.init();
    }

    public abstract void init();

    public void init(Minecraft minecraft, int width, int height) {
        this.minecraft = minecraft;
        this.width = width;
        this.height = height;
        this.init();
    }

    //update
    public void render() {
        for (Component p: this.components) {
            p.render();
        }
    }

    public void tick() {
        for (Component p:this.components){
            p.resize(Display.getWidth()/ GameSetting.instance.GUIScale,Display.getHeight()/GameSetting.instance.GUIScale);
            p.tick(Mouse.getX()/ GameSetting.instance.GUIScale,(-Mouse.getY()+Display.getHeight())/GameSetting.instance.GUIScale);
        }
    }

    //attribute
    public boolean isInGameGUI(){
        return false;
    }
}
