package tcgstudio2022.openMC.connection;

public class MinecraftServer{}
/*

implements Runnable,
ServerListener {
    private SocketServer socketServer;
    private Map<SocketConnection, ClientIO> clientMap = new HashMap<SocketConnection, ClientIO>();
    private List<ClientIO> clients = new ArrayList<ClientIO>();

    public MinecraftServer(byte[] ips, int port) throws IOException {
        this.socketServer = new SocketServer(ips, port, this);
    }

    @Override
    public void clientConnected(SocketConnection serverConnection) {
        ClientIO client = new ClientIO(this, serverConnection);
        this.clientMap.put(serverConnection, client);
        this.clients.add(client);
    }

    public void disconnect(ClientIO client) {
        this.clientMap.remove(client.serverConnection);
        this.clients.remove(client);
    }

    @Override
    public void clientException(SocketConnection serverConnection, Exception e) {
        ClientIO client = this.clientMap.get(serverConnection);
        client.handleException(e);
    }

    @Override
    public void run() {
        while (true) {
            this.tick();
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException interruptedException) {
            }
        }
    }

    private void tick() {
        try {
            this.socketServer.tick();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] arrby = new byte[4];
        arrby[0] = 127;
        arrby[3] = 1;
        MinecraftServer server = new MinecraftServer(arrby, 20801);
        Thread thread = new Thread(server);
        thread.start();
    }
}

 */
