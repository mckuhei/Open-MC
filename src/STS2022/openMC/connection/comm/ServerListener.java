package tcgstudio2022.openMC.connection.comm;

public interface ServerListener {
    public void clientConnected(SocketConnection var1);

    public void clientException(SocketConnection var1, Exception var2);
}
