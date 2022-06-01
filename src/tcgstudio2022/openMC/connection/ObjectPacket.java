package tcgstudio2022.openMC.connection;

import java.io.Serializable;

public class ObjectPacket implements Serializable {
    enum Datatype{
        CHUNK,
        ENTITY,
        CONTROL
    }

    public Datatype type;
    public Object data;
    public ObjectPacket(Object data,Datatype type){
        this.data=data;
        this.type=type;
    }
}
