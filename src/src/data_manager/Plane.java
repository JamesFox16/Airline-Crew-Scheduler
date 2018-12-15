package data_manager;

public class Plane {
    private String planeType;

    public Plane (String planeType) {
        this.planeType = planeType;

    }
    public String getPlaneType(){
        return planeType;
    }

    public void setPlaneType(String planeType){
        this.planeType = planeType;
    }
}
