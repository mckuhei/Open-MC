package com.SunriseStudio.TeamTCG.openmc.connection.comm;

public interface ServerListener {
    public void clientConnected(SocketConnection var1);

    public void clientException(SocketConnection var1, Exception var2);
}
