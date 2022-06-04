package com.SunriseStudio.TeamTCG.openmc.world.block;


import com.SunriseStudio.TeamTCG.openmc.util.collections.Vector3;

public enum BlockFacing {
    Up(0),
    Down(1),
    West(2),
    East(3),
    North(4),
    South(5);

    private int numID;
    BlockFacing(int id){
        this.numID=id;
    }

    public int getNumID() {
        return numID;
    }

    BlockFacing fromId(int id){
        BlockFacing facing=Up;
        switch (id){
            case 0:facing= Up;
            case 1:facing= Down;
            case 2:facing= West;
            case 3:facing= East;
            case 4:facing= North;
            case 5:facing= South;
        }
        return facing;
    }

    Vector3<Long> clip(long x,long y,long z){
        switch (this){
            case Up:y++;
            case Down:y--;
            case West:z++;
            case East:z--;
            case North:x++;
            case South:x--;
        }
        return new Vector3<>(x, y, z);
    }
}
