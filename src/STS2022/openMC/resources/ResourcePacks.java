package tcgstudio2022.openMC.resources;

import java.io.InputStream;
import java.util.ArrayList;

public class ResourcePacks {
    public static ResourcePacks instance=new ResourcePacks();
    public ArrayList<ResourcePack> resourcePacks=new ArrayList<>();
    public InputStream getResource(String path){
        InputStream inputStream=null;
        for (ResourcePack resourcePack:resourcePacks){
            //inputStream=resourcePack.getInput(path);
            if(inputStream!=null){
                break;
            }
        }
        if (inputStream==null){
            inputStream=this.getClass().getResourceAsStream(path);
        }
        if (inputStream==null){
            inputStream=this.getClass().getResourceAsStream("/assets/minecraft/fallback.jsysb");
        }
        return inputStream;
    }
}
