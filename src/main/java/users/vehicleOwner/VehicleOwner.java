package users.vehicleOwner;

import java.util.ArrayList;

public class VehicleOwner {

    private String vehicleOwnerId;
    private String password;
    private ArrayList<Vehicle> vechicleList;

    public VehicleOwner(String vehicleOwnerId, String password) {
        this.vehicleOwnerId = vehicleOwnerId;
        this.password = password;
    }

    public String getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Vehicle> getVechicleList() {
        return vechicleList;
    }
}
