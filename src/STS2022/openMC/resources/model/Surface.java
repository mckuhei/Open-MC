package tcgstudio2022.openMC.resources.model;

import tcgstudio2022.openMC.world.Level;
import org.joml.Vector2d;
import org.joml.Vector3d;

public class Surface {
    public Vector3d vertex0=new Vector3d();
    public Vector3d vertex1=new Vector3d();
    public Vector3d vertex2=new Vector3d();
    public Vector3d vertex3=new Vector3d();
    public Vector2d tc0=new Vector2d();
    public Vector2d tc1=new Vector2d();
    public Vector2d tc2=new Vector2d();
    public Vector2d tc3=new Vector2d();
    public String texture;
    public String culling;

    public boolean shouRender(long x, long y, long z, Level world){
        return switch (this.culling) {
            case ("solid_face_0") -> !world.isSolidTile(x, y - 1, z);
            case ("solid_face_1") -> !world.isSolidTile(x, y + 1, z);
            case ("solid_face_2") -> !world.isSolidTile(x, y, z - 1);
            case ("solid_face_3") -> !world.isSolidTile(x, y, z + 1);
            case ("solid_face_4") -> !world.isSolidTile(x - 1, y, z);
            case ("solid_face_5") -> !world.isSolidTile(x + 1, y, z);
            case ("equal_face_0") -> !(world.getTile(x, y - 1, z) == 1);
            case ("equal_face_1") -> !world.isSolidTile(x, y + 1, z);
            case ("equal_face_2") -> !world.isSolidTile(x, y, z - 1);
            case ("equal_face_3") -> !world.isSolidTile(x, y, z + 1);
            case ("equal_face_4") -> !world.isSolidTile(x - 1, y, z);
            case ("equal_face_5") -> !world.isSolidTile(x + 1, y, z);
            default -> true;
        };
    }

}
