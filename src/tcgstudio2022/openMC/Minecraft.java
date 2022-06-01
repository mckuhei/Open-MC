/*
    Copyright (c) [Year] [name of copyright holder]
    [Software Name] is licensed under Mulan PSL v2.
    You can use this software according to the terms and conditions of the Mulan PSL v2.
    You may obtain a copy of Mulan PSL v2 at:
    http://license.coscl.org.cn/MulanPSL2
    THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
    EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
    MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
    See the Mulan PSL v2 for more details.
 */
package tcgstudio2022.openMC;

import tcgstudio2022.openMC.gui.screen.HUDScreen;
import tcgstudio2022.openMC.gui.screen.Screen;
import tcgstudio2022.openMC.gui.screen.TitleScreen;
import tcgstudio2022.openMC.render.CameraManager;
import tcgstudio2022.openMC.render.renderer.ContentWorldRenderer;
import tcgstudio2022.openMC.util.phys.HitResult;
import tcgstudio2022.openMC.util.phys.AABB;
import tcgstudio2022.openMC.resources.textures.Textures;

import java.nio.IntBuffer;


import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.world.particle.ParticleEngine;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import tcgstudio2022.openMC.util.LogHandler;
import tcgstudio2022.openMC.util.Timer;
import tcgstudio2022.openMC.world.Level;
import tcgstudio2022.openMC.world.block.Tile;

//todo:add server net support;
//todo:add resource support;
//todo:add hitting support;
//todo:add shader support;
//todo:add inventory support

public class Minecraft implements Runnable{
    public static final String VERSION="alpha-0.0.3";


    public Level clientWorld;

    public boolean fullscreen;
    public int width;
    public int height;
    public int FPS;
    public ContentWorldRenderer worldRenderer;
    private Screen screen;

    public Player player;
    public Textures textures;
    private int editMode = 0;

    public Timer timer=new Timer(20.0f);

    private volatile boolean running = false;
    private HitResult hitResult = null;
    private ParticleEngine particleEngine;


    public Minecraft( GameSetting config) {
        this.width = config.displayWidth;
        this.height = config.displayHeight;
        this.fullscreen = config.fullscreen;
        this.textures = new Textures();
    }

    public void init() throws LWJGLException {
        LogHandler.log("start init", LogHandler.Errors.INFO, "");

        Display.setDisplayMode(new DisplayMode(this.width,this.height));
        Display.setTitle("OpenMC-"+VERSION);
        Display.setResizable(true);
        Display.create((new PixelFormat()).withDepthBits(24).withSamples(GameSetting.instance.FXAA));
        Keyboard.create();
        Mouse.create();

        LogHandler.checkGLError("openGL:pre startup");
        GL11.glEnable(3553);
        GL11.glShadeModel(7425);
        GL11.glClearColor(223f/255f,245f/255f,1, 0.0f);
        GL11.glClearDepth(1.0);
        GL11.glEnable(2929);
        GL11.glDepthFunc(515);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        LogHandler.checkGLError("openGL:pose startup");


        IntBuffer imgData = BufferUtils.createIntBuffer(256);
        imgData.clear().limit(256);
        GL11.glViewport(0, 0, this.width, this.height);

        this.setScreen(new TitleScreen());
        LogHandler.checkGLError("Post startup");
        LogHandler.log("finish init", LogHandler.Errors.INFO, "com/mojang/minecraft3");
        this.clientWorld=new Level(0);
        this.player=new Player(this.clientWorld);
        player.setPos(1145141919810L,256,1024);
        this.particleEngine=new ParticleEngine(this.clientWorld,this.textures);
        this.worldRenderer =new ContentWorldRenderer(this.clientWorld,this.player,this.particleEngine);
    }

    @Override
    public void run() {
        this.running = true;
        try {
            this.init();
        }
        catch (Exception e) {
            e.printStackTrace();
            LogHandler.log("fucked while starting", LogHandler.Errors.INFO,"client/startup");
            return;
        }
        long lastTime = System.currentTimeMillis();
        int frames = 0;

        try {
            try {
                while (this.running) {
                    if (Display.isCloseRequested()) {
                        this.stop();
                    }

                    LogHandler.checkGLError("Pre render");
                    this.render(timer.interpolatedTime);
                    LogHandler.checkGLError("Post render");
                    ++frames;
                    timer.advanceTime();
                    for (int i = 0; i < timer.ticks; ++i) {
                        this.tick();
                    }

                    while (System.currentTimeMillis() >= lastTime + 1000L) {
                        this.FPS = frames;

                        lastTime += 1000L;
                        frames = 0;

                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                this.stop();
            }
        }
        finally {
            this.stop();
        }
    }

    public void stop() {
        this.running = false;
        LogHandler.log("client stopping", LogHandler.Errors.INFO,"client/main");
        //builtinServer.playerLeaveWorld(this.player);
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
        LogHandler.log("client stopped", LogHandler.Errors.INFO,"client/main");
        System.exit(0);
    }

    private void handleMouseClick() {
        if (this.editMode == 0) {
            if (this.hitResult != null) {
                Tile oldTile = Tile.tiles[this.clientWorld.getTile(this.hitResult.x, this.hitResult.y, this.hitResult.z)];
                boolean changed = this.clientWorld.setTile(this.hitResult.x, this.hitResult.y, this.hitResult.z, 0);
                if (oldTile != null && changed) {
                    oldTile.destroy(this.clientWorld, this.hitResult.x, this.hitResult.y, this.hitResult.z, this.worldRenderer.particleEngine);
                }
            }
            //this.builtinServer.world.setTile(this.hitResult.x, this.hitResult.y, this.hitResult.z,0);
        } else if (this.hitResult != null) {
            AABB aabb;
            int x = this.hitResult.x;
            int y = this.hitResult.y;
            int z = this.hitResult.z;
            if (this.hitResult.f == 0) {
                --y;
            }
            if (this.hitResult.f == 1) {
                ++y;
            }
            if (this.hitResult.f == 2) {
                --z;
            }
            if (this.hitResult.f == 3) {
                ++z;
            }
            if (this.hitResult.f == 4) {
                --x;
            }
            if (this.hitResult.f == 5) {
                ++x;
            }
            if ((aabb = Tile.tiles[1].getAABB(x, y, z)) == null || this.isFree(aabb)) {
                this.clientWorld.setTile(x, y, z, 1);
                //this.builtinServer.world.setTile(this.hitResult.x, this.hitResult.y, this.hitResult.z,this.paintTexture);
            }
        }
    }

    public void tick() {
        while (Mouse.next()){
            if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
                this.handleMouseClick();
                if(Display.isActive()&&this.screen instanceof HUDScreen){
                    Mouse.setGrabbed(true);
                }
            }
            if (Mouse.getEventButton() == 1 && Mouse.getEventButtonState()) {
                this.editMode = (this.editMode + 1) % 2;
            }
        }
        this.screen.tick();
        this.clientWorld.tick();

        this.worldRenderer.particleEngine.tick();

        //System.gc();
    }

    private boolean isFree(AABB aabb) {
        if (this.player.collisionBox.intersects(aabb)) {
            return false;
        }
        return true;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        if (screen != null) {
            int screenWidth = this.width * 240 / this.height;
            int screenHeight = this.height * 240 / this.height;
            screen.init(this, screenWidth, screenHeight);
        }
    }

    public void render(float a) {
        this.width=Display.getWidth();
        this.height=Display.getHeight();
        GL11.glClear(16640);
        if (this.screen.isInGameGUI()) {
            worldRenderer.render(a,this.width,this.height);
        }
        LogHandler.checkGLError("render world");
        GL11.glEnable(3553);
        int screenWidth = this.width * 240 / this.height;
        int screenHeight = this.height * 240 / this.height;
        CameraManager.setupOrthogonalCamera(0,0, this.width, this.height, this.width/GameSetting.instance.GUIScale, this.height/GameSetting.instance.GUIScale);

        if (this.screen != null) {
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER,0.0f);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.screen.render();
        }

        LogHandler.checkGLError("Rendered gui");
        Display.update();
    }
}
