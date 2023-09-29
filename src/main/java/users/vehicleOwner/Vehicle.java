package users.vehicleOwner;

public class Vehicle {
    private String vehicleModel;
    private String vehicleMake;
    private int vehicleYear;
    private boolean inUse;

    /**
     * method will create a new vehicle object
     *
     * @param vehicleModel
     * @param vehicleMake
     * @param vehicleYear
     */
    public Vehicle(String vehicleModel, String vehicleMake, int vehicleYear) {
        this.vehicleModel = vehicleModel;
        this.vehicleMake = vehicleMake;
        this.vehicleYear = vehicleYear;
        this.inUse = false;

    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public boolean getInUse(){
        return inUse;
    }

}
