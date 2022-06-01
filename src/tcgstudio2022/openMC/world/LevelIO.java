package tcgstudio2022.openMC.world;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

public class LevelIO {
    private static final int MAGIC_NUMBER = 656127880;
    private static final int CURRENT_VERSION = 1;
    private LevelLoaderListener levelLoaderListener;
    public String error = null;

    public LevelIO(LevelLoaderListener levelLoaderListener) {
        this.levelLoaderListener = levelLoaderListener;
    }

    public boolean load(Level world, InputStream in) throws IOException {
        DataInputStream dis;
        block5: {
            block4: {
                this.levelLoaderListener.beginLevelLoading("Loading world");
                this.levelLoaderListener.levelLoadUpdate("Reading.. If the game freezes, delete the world.dat");
                try {
                    dis = new DataInputStream(new GZIPInputStream(in));
                    int magic = dis.readInt();
                    if (magic == 656127880) break block4;
                    this.error = "Bad world file format";
                    return false;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    this.error = "Failed to load world: " + e.toString();
                    return false;
                }
            }
            byte version = dis.readByte();
            if (version <= 1) break block5;
            this.error = "Bad world file format";
            return false;
        }
        String name = dis.readUTF();
        String creator = dis.readUTF();
        long createTime = dis.readLong();
        short width = dis.readShort();
        short height = dis.readShort();
        short depth = dis.readShort();
        byte[] blocks = new byte[width * height * depth];
        dis.readFully(blocks);
        dis.close();
        //world.setData(width, depth, height, blocks);
        world.name = name;

        return true;
    }

    public boolean loadLegacy(Level world, InputStream in) {
        this.levelLoaderListener.beginLevelLoading("Loading world");
        this.levelLoaderListener.levelLoadUpdate("Reading.. If the game freezes, delete the world.dat");
        try {
            DataInputStream dis = new DataInputStream(new GZIPInputStream(in));
            String name = "--";
            String creator = "unknown";
            long createTime = 0L;
            int width = 256;
            int height = 256;
            int depth = 64;
            byte[] blocks = new byte[width * height * depth];
            dis.readFully(blocks);
            dis.close();
            //world.setData(width, depth, height, blocks);
            world.name = name;

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.error = "Failed to load world: " + e.toString();
            return false;
        }
    }

    public void save(Level world, OutputStream out) {
        /*try {
            DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(out));
            dos.writeInt(656127880);
            dos.writeByte(1);
            dos.writeUTF(world.name);
            dos.writeUTF(world.creator);
            dos.writeLong(world.createTime);
            dos.writeShort(world.width);
            dos.writeShort(world.height);
            dos.writeShort(world.depth);
            dos.write(world.blocks);
            dos.close();
        }
        catch (Exception Biome) {
            Biome.printStackTrace();
        }*/
    }
}
