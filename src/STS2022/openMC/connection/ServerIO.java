package tcgstudio2022.openMC.connection;

import tcgstudio2022.openMC.world.entity.Player;
import tcgstudio2022.openMC.util.LogHandler;
import tcgstudio2022.openMC.util.Timer;
import tcgstudio2022.openMC.world.Level;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.Random;

public class ServerIO implements Runnable{

    public int port;


    public void sendDataToClient(ObjectPacket.Datatype datatype, Object data, InetAddress clientIP) throws IOException {
        MulticastSocket socket=new MulticastSocket(this.port);
        socket.setTimeToLive(255);
        socket.joinGroup(clientIP);
        byte[] buffer=new byte[114514];

        DatagramPacket packet=new DatagramPacket(buffer, buffer.length,clientIP,port);
        socket.send(packet);

    }



    public Level world;
    private boolean running=true;
    private int tps;
    private Timer timer=new Timer(20.0f);

    public ServerIO(){}

    public void run(){
        LogHandler.log("pre-startup", LogHandler.Errors.INFO,"server/world");
        checkWorld();
        LogHandler.log("post-startup", LogHandler.Errors.INFO,"server/world");
        long lastTime = System.currentTimeMillis();
        while (this.running) {
            int ticks = 0;
            timer.advanceTime();
            for (int i = 0; i < timer.ticks; ++i) {
                this.world.tick();
                ticks++;
            }
            while (System.currentTimeMillis() >= lastTime + 1000L) {
                this.tps = ticks;
                ticks = 0;
                lastTime += 1000L;
            }
        }
    }

    public void loadWorld(File f){}

    public void saveWorld(){
        checkWorld();
        this.world.toJson();
    }

    public void resetWorld(long seed){
        this.world=new Level(seed);
    }

    public void playerJoinWorld(Player player){
        checkWorld();
        this.world.entities.add(player);
        LogHandler.log("player"+player.toString()+"joined", LogHandler.Errors.INFO,"server/world");
    }
    public void playerLeaveWorld(Player player){
        checkWorld();
        this.world.entities.remove(player);
        LogHandler.log("player"+player.toString()+"leaved", LogHandler.Errors.INFO,"server/world");
    }


    public void checkWorld(){
        if(world==null){
            LogHandler.log("world is null while player joining world", LogHandler.Errors.ERROR,"server/world");
            long seed=new Random().nextLong();
            resetWorld(seed);
            LogHandler.log("world reset in seed"+seed, LogHandler.Errors.INFO,"server/world");
        }
    }
}
