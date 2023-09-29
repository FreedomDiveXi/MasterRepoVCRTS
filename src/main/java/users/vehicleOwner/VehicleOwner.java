package users.vehicleOwner;

import java.util.ArrayList;

public class VehicleOwner {

    private String vehicleOwnerId;
    private String vehicleOwnerPassword;
    private ArrayList<Vehicle> vechicleList;


    public VehicleOwner(String vehicleOwnerId, String vehicleOwnerPassword) {
        this.vehicleOwnerId = vehicleOwnerId;
        this.vehicleOwnerPassword = vehicleOwnerPassword;
        this.vechicleList = new ArrayList<Vehicle>();
    }

    public String getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public String getVehiclePassword() {
        return vehicleOwnerPassword;
    }

    public ArrayList<Vehicle> getVechicleList() {
        return vechicleList;
    }
}

