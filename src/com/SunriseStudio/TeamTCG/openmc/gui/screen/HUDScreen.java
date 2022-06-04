package com.SunriseStudio.TeamTCG.openmc.gui.screen;

import com.SunriseStudio.TeamTCG.openmc.Minecraft;
import com.SunriseStudio.TeamTCG.openmc.render.ShapeRenderer;
import com.SunriseStudio.TeamTCG.openmc.render.renderer.ChunkRenderer;
import com.SunriseStudio.TeamTCG.openmc.render.renderer.FontRenderer;
import com.SunriseStudio.TeamTCG.openmc.resources.textures.TextureManagerDirect;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.text.DecimalFormat;

public class HUDScreen extends Screen {
    private boolean debugScreen;
    @Override
    public void render() {
        DecimalFormat format=new DecimalFormat();
        format.applyPattern("#.000");
        this.minecraft.player.turn(Mouse.getDX(),Mouse.getDY());
        if (this.debugScreen) {
            FontRenderer.render("openMC-" + Minecraft.VERSION, 2, 2, 16777215, 8, FontRenderer.Alignment.LEFT);
            FontRenderer.render("帧率:" + this.minecraft.FPS, 2, 10, 16777215, 8, FontRenderer.Alignment.LEFT);
            FontRenderer.render("显示:" + Display.getWidth() + "/" + Display.getHeight(), 2, 20, 16777215, 8 , FontRenderer.Alignment.LEFT);
            ChunkRenderer cr= (ChunkRenderer) this.minecraft.worldRenderer.renderers.get("openMC/renderer:chunk");
            FontRenderer.render("地形渲染(总数/可见/更新):"+cr.allCount+"/"+cr.visibleCount+"/"+cr.updateCount,2,30,16777215,8, FontRenderer.Alignment.LEFT);

            //OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
            //FontRenderer.render("系统信息" + os.getName(), 2, 70, 16777215, 8, FontRenderer.Alignment.LEFT);//StringBuilder你值得拥有
            FontRenderer.render("位置（x/y/z）:" + format.format(this.minecraft.player.x) + "/" + format.format(this.minecraft.player.y) + "/" + format.format(this.minecraft.player.z), 2, 50, 16777215, 8, FontRenderer.Alignment.LEFT);
            FontRenderer.render("相机角度（yaw/pitch/roll）:"+ format.format(this.minecraft.player.xRot) + "/" + format.format(this.minecraft.player.yRot) + "/" + format.format(this.minecraft.player.zRot),2,60,0xFFFFFF,8, FontRenderer.Alignment.LEFT);
        }
        TextureManagerDirect.bind("/assets/minecraft/textures/gui/actionbar.png");
        ShapeRenderer.drawRectUV(Display.getWidth()/2d-91,Display.getWidth()/2d+91, Display.getHeight()-22,Display.getHeight(),0,0,0,1,0,1);
        int slotBase= (int) (Display.getWidth()/2d+20*(-4.5+minecraft.player.selectSlot));
        TextureManagerDirect.bind("/assets/minecraft/textures/gui/actionbar_selection.png");
        ShapeRenderer.drawRectUV((slotBase-2),
                (slotBase+22),
                Display.getHeight()-(21),
                Display.getHeight()+1,0,0,0,1,0,1);
    }

    @Override
    public void init() {

    }


    @Override
    public void tick() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                    Mouse.setGrabbed(false);
                    this.minecraft.setScreen(new PauseScreen());
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_Z) {
                    this.minecraft.player.flyingMode=!this.minecraft.player.flyingMode;
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_F3) {
                    this.debugScreen=!this.debugScreen;
                }
            }
            this.minecraft.player.setKey(Keyboard.getEventKey(), Keyboard.getEventKeyState());
        }
        while (Mouse.next()){

        }
    }

    @Override
    public boolean isInGameGUI() {
        return true;
    }
}
