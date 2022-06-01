package tcgstudio2022.openMC.world.biome;

import tcgstudio2022.openMC.world.block.Tile;

import java.util.HashMap;

public class Biomes {
    public static HashMap<String,Biome> biomesMap=new HashMap<>(114514);

    public static void register(String s,Biome b){
        biomesMap.put(s,b);
    }

    public static void get(String s){
        biomesMap.get(s);
    }

    @Deprecated
    public static void unregister(String s){
        biomesMap.remove(s);
    }


    static{
        register("plain",new Biome(3,0.1, new int[]{Tile.dirt.id, Tile.dirt.id, Tile.grass.id}));
        register("mountain",new Biome(320,0.1, new int[]{Tile.dirt.id, Tile.dirt.id, Tile.grass.id}));
    }





}
