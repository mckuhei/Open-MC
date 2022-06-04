package com.SunriseStudio.TeamTCG.openmc.world.biome;

import com.SunriseStudio.TeamTCG.openmc.world.block.Tile;

public class Biome {
    //terrain data
    public double height;//relative height,real height will be added 128
    public double contrast;//contrast,base noise will multiply this value
    public int[] coverBlock;//for example:minecraft plain biomes cover will be "dirt,dirt,grass" here.

    //structure data
    //gugugu

    public Biome(double height,double contrast,int[] coverBlock){
        this.height=height;
        this.contrast=contrast;
        this.coverBlock=coverBlock;
    }

    public static Biome getDefault(){
        return new Biome(4,16,new int[]{Tile.dirt.id,Tile.dirt.id,Tile.grass.id});
    }
}
