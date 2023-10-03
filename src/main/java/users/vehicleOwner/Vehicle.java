package users.vehicleOwner;

import java.time.*;

public class Vehicle {
    private String vehicleModel;
    private String vehicleMake;
    private int vehicleYear;
    private boolean inUse;
    private LocalDateTime dateTimeNow;

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
        this.dateTimeNow = LocalDateTime.now();
    }
    public boolean isInUse() {
        return inUse;
    }
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
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

    public LocalDateTime getDateTimeNow() { return dateTimeNow; }

}
