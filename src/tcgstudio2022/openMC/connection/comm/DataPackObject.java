package tcgstudio2022.openMC.connection.comm;

public class DataPackObject<DataClass> {
    private DataClass data;
    public DataClass getObject(){
        return this.data;
    }
    public void setObject(DataClass data){
        this.data=data;
    }
}
