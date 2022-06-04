package com.SunriseStudio.TeamTCG.openmc.connection.comm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class SocketServer {
    private ServerSocketChannel ssc;
    private ServerListener serverListener;
    private List<SocketConnection> connections = new LinkedList<SocketConnection>();

    public SocketServer(byte[] ips, int port, ServerListener serverListener) throws IOException {
        this.serverListener = serverListener;
        InetAddress hostip = InetAddress.getByAddress(ips);
        this.ssc = ServerSocketChannel.open();
        this.ssc.socket().bind(new InetSocketAddress(hostip, port));
        this.ssc.configureBlocking(false);
    }

    public void tick() throws IOException {
        SocketChannel socketChannel;
        while ((socketChannel = this.ssc.accept()) != null) {
            try {
                socketChannel.configureBlocking(false);
                SocketConnection socketConnection = new SocketConnection(socketChannel);
                this.connections.add(socketConnection);
                this.serverListener.clientConnected(socketConnection);
            }
            catch (IOException e) {
                socketChannel.close();
                throw e;
            }
        }
        for (int i = 0; i < this.connections.size(); ++i) {
            SocketConnection socketConnection = this.connections.get(i);
            if (!socketConnection.isConnected()) {
                socketConnection.disconnect();
                this.connections.remove(i--);
                continue;
            }
            try {
                socketConnection.tick();
                continue;
            }
            catch (Exception e) {
                socketConnection.disconnect();
                this.serverListener.clientException(socketConnection, e);
            }
        }
    }
}
