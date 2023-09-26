package users.vehicleOwner;

import java.util.ArrayList;

public class VehicleOwner {

    private String vehicleOwnerId;
    private String vehiclePassword;
    private ArrayList<Vehicle> vechicleList;


    public VehicleOwner(String vehicleOwnerId, String vehiclePassword) {
        this.vehicleOwnerId = vehicleOwnerId;
        this.vehiclePassword = vehiclePassword;

    }

    public String getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public String getVehiclePassword() {
        return vehiclePassword;
    }

    public ArrayList<Vehicle> getVechicleList() {
        return vechicleList;
    }
}
