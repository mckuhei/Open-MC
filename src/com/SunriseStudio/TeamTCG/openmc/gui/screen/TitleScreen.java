package com.SunriseStudio.TeamTCG.openmc.gui.screen;

import com.SunriseStudio.TeamTCG.openmc.GameSetting;
import com.SunriseStudio.TeamTCG.openmc.gui.LoadingScreenTask;
import com.SunriseStudio.TeamTCG.openmc.gui.component.Button;
import com.SunriseStudio.TeamTCG.openmc.gui.component.Label;
import com.SunriseStudio.TeamTCG.openmc.render.ShapeRenderer;
import com.SunriseStudio.TeamTCG.openmc.render.renderer.FontRenderer;
import com.SunriseStudio.TeamTCG.openmc.resources.textures.TextureManagerDirect;
import com.SunriseStudio.TeamTCG.openmc.gui.layout.Border;
import com.SunriseStudio.TeamTCG.openmc.gui.layout.OriginLayout;
import org.lwjgl.opengl.Display;

public class TitleScreen extends Screen {

    @Override
    public void init() {
        this.components.clear();
        {
            Button singlePlayerButton=new Button(0xFFFFFF,0xFFFFFF,"单人游戏");
            singlePlayerButton.setLayout(new OriginLayout(0,-10,300,30, OriginLayout.Origin.MIDDLE_MIDDLE,0));
            singlePlayerButton.setBorder(new Border(0,0,4,4));
            singlePlayerButton.setListener(() -> {
            this.minecraft.setScreen(new LoadingScreen(new TitleScreen(), new HUDScreen(), new LoadingScreenTask() {
                @Override
                public void run() {
                    for (int i = 0; i < 101; i++) {
                        this.setText("加入世界中...");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            this.cancel();
                        }
                        this.setStatus(i);
                    }
                }
            }));//actullyJoinTheGame
            });
            this.components.add(singlePlayerButton);
        }//single player button
        {
            Button multiPlayerButton=new Button(0xFFFFFF,0xFFFFFF,"多人游戏");
            multiPlayerButton.setLayout(new OriginLayout(0,20,300,30, OriginLayout.Origin.MIDDLE_MIDDLE,0));
            multiPlayerButton.setBorder(new Border(0,0,4,4));
            multiPlayerButton.setListener(() -> {
                this.minecraft.setScreen(new HUDScreen());//actullyJoinTheGame
            });
            this.components.add(multiPlayerButton);
        }//multi player button
        {
            Button settingButton=new Button(0xFFFFFF,0xFFFFFF,"设置");
            settingButton.setLayout(new OriginLayout(-75,50,150,30, OriginLayout.Origin.MIDDLE_MIDDLE,0));
            settingButton.setBorder(new Border(0,4,4,4));
            settingButton.setListener(() -> {
                this.minecraft.setScreen(new SettingScreen());//actullyJoinTheGame
            });
            this.components.add(settingButton);
        }//setting button
        {
            Button quitButton=new Button(0xFFFFFF,0xFFFFFF,"退出");
            quitButton.setLayout(new OriginLayout(75,50,150,30, OriginLayout.Origin.MIDDLE_MIDDLE,0));
            quitButton.setBorder(new Border(4,4,4,4));
            quitButton.setListener(() -> {
                this.minecraft.stop();
            });
            this.components.add(quitButton);
        }//multi player button
        {
            Label title=new Label("openMC",56,16777215,0, FontRenderer.Alignment.MIDDLE);
            title.setLayout(new OriginLayout(0,-70,0,56, OriginLayout.Origin.MIDDLE_MIDDLE,0));
            this.components.add(title);
        }
        {
            Label title=new Label("——基于MC-classic版本的实验平台",12,16777215,0, FontRenderer.Alignment.RIGHT);
            title.setLayout(new OriginLayout(140,-30,0,12, OriginLayout.Origin.MIDDLE_MIDDLE,0));
            this.components.add(title);
        }
    }

    @Override
    public void render() {
        TextureManagerDirect.bind("/assets/minecraft/textures/gui/bg.png");
        ShapeRenderer.begin();
        ShapeRenderer.drawRectUV(0,Display.getWidth()/ GameSetting.instance.GUIScale,0,Display.getHeight()/GameSetting.instance.GUIScale,-1,-1,0,1,0,1);
        ShapeRenderer.end();
        super.render();
    }
}
