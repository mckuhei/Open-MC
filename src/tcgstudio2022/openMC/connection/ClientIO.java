package tcgstudio2022.openMC.connection;

import tcgstudio2022.openMC.world.entity.Entity;
import tcgstudio2022.openMC.Minecraft;
import tcgstudio2022.openMC.world._Chunk;

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

