package com.SunriseStudio.TeamTCG.openmc.world.chunk;

public class ChunkLoadTicket {
    private ChunkLoadLevel chunkLoadLevel;
    private int time;

    public ChunkLoadTicket(ChunkLoadLevel loadLevel,int ticks){
        this.time=ticks;
        this.chunkLoadLevel=loadLevel;
    }

    public void setChunkLoadLevel(ChunkLoadLevel chunkLoadLevel) {
        this.chunkLoadLevel = chunkLoadLevel;
    }

    public ChunkLoadLevel getChunkLoadLevel() {
        return chunkLoadLevel;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void tickTime(){
        this.time--;
    }
}
