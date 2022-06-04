package com.SunriseStudio.TeamTCG.openmc.world.worldGen.synth;

import com.SunriseStudio.TeamTCG.openmc.util.math.MathHelper;

import java.util.Random;

public class ScaledNoise extends Synth{
    public int grid_size;
    public int verticle_scale;
    public PerlinNoise noise;

    public ScaledNoise(int grid_size,int verticle_scale,long seed,int noise_scale){
        this.grid_size=grid_size;
        this.verticle_scale=verticle_scale;
        this.noise=new PerlinNoise(new Random(seed),noise_scale);
    }


    @Override
    public double getValue(double x, double z) {
        double x0=x/grid_size;
        double z0=z/grid_size;
        double x1=x/grid_size+1;
        double z1=z/grid_size+1;

        double grid_00_height=this.noise.getValue(x/grid_size,z/grid_size)*verticle_scale;
        double grid_11_height=this.noise.getValue(x/grid_size+1,z/grid_size+1)*verticle_scale;
        double grid_10_height=this.noise.getValue(x/grid_size+1,z/grid_size)*verticle_scale;
        double grid_01_height=this.noise.getValue(x/grid_size,z/grid_size+1)*verticle_scale;

        double xp=(x-x0)/(x1-x0);
        double zp=(z-z0)/(z1-z0);

        double xl_0= MathHelper.smooth_interpolate(grid_00_height,grid_10_height,xp);
        double xl_1= MathHelper.smooth_interpolate(grid_01_height,grid_11_height,xp);
        double a=MathHelper.smooth_interpolate(xl_0,xl_1,zp);
        return a;

    }
}
