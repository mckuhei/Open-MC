package tcgstudio2022.openMC.connection;

import java.io.*;
import java.net.Socket;

public class ServerConnection {
    public ObjectInputStream chunkStream;
    public DataOutputStream controlStream;

    public Socket connectionSocket;

    public ServerConnection(String ip,int port) throws IOException {
        this.connectionSocket=new Socket(ip,port);

        this.chunkStream= new ObjectInputStream(connectionSocket.getInputStream());
        this.controlStream=new DataOutputStream(connectionSocket.getOutputStream());
    }
}
