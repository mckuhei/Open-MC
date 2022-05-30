package tcgstudio2022.openMC;

public class GameSetting {
    public static GameSetting instance=new GameSetting();

    //render
    public int renderDistance =2;
    public int renderLoadingDistance=16;
    public float fov=70;
    //display
    public int GUIScale=2;
    public int maxFrames=120;
    public boolean fullscreen=false;
    public int displayWidth=1280;
    public int displayHeight=720;
    public int maxUpdatesPreFrame=1;

    public int FXAA=3;
    public String gamePath="C://openMC";
    public int renderDistanceFar=8;
}
