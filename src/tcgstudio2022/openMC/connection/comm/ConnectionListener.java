package tcgstudio2022.openMC.connection.comm;

import java.nio.ByteBuffer;

public interface ConnectionListener {
    public void handleException(Exception var1);

    public void command(byte var1, int var2, ByteBuffer var3);
}
