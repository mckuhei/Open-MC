package tcgstudio2022.openMC;

public class Start {
    public static void main(String[] args) {

        System.out.println("starting...");
        handleArgs(args);
        Minecraft minecraft = new Minecraft(GameSetting.instance);
        new Thread(minecraft).start();
    }

    public static void handleArgs(String[] args){
        for (int i = 0; i < args.length; i++) {
            if(args[i].contains("-openMC:args/display/GUIScale=")){
                GameSetting.instance.GUIScale= Integer.parseInt(args[i].substring(args.length-1, args.length));
            }

            if(args[i].contains("-openMC:args/display/fullScreen=")){
                int mode=Integer.parseInt(args[i].substring(args.length-1, args.length));
                if(mode==0) GameSetting.instance.fullscreen=false;
                if(mode==1) GameSetting.instance.fullscreen=true;
            }

            if(args[i].contains("-openMC:args/display/maxFrames=")){
                GameSetting.instance.maxFrames= Integer.parseInt(args[i].substring(args.length-1, args.length));
            }

            if(args[i].contains("-openMC:args/display/displayWidth")){

            }

            if(args[i].contains("-openMC:args/display/displayHeight")){

            }
        }


    }
}
