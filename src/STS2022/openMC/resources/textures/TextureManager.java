package tcgstudio2022.openMC.resources.textures;

import java.util.HashMap;

public class TextureManager {
    public HashMap<String, Texture> textures;

    public void create(String id,Texture t){this.textures.put(id,t);}



}
