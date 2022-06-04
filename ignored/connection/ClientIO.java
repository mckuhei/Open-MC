package com.SunriseStudio.TeamTCG.openmc.connection;

import com.SunriseStudio.TeamTCG.openmc.Minecraft;
import com.SunriseStudio.TeamTCG.openmc.world.entity.Entity;
import com.SunriseStudio.TeamTCG.openmc.world.chunk._Chunk;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;


public class ClientIO {
    InetAddress serverAddr;
    public ObjectOutputStream chunkStream;
    public DataOutputStream controlStream;






    private ServerIO serverIO;
    private Minecraft client;
    public ClientIO(Minecraft instance){
        this.client=instance;

    }

    public _Chunk getChunkFromServer(){
        return null;
    }

    public Entity getEntityFromServer(){
        return null;
    }

    public void connectToServer(String ip,int port){

    }
    public void disConnectFromServer(String ip,int port){


    }



}

