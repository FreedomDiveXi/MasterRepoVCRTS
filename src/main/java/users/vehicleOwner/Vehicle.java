package users.vehiclePerson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Vehicle {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDateTime now = LocalDateTime.now();
    private LocalDateTime creationTimeStamp;
    private String vehicleModel;
    private String vehicleMake;
    private int vehicleYear;
    private boolean inUse;


    /**
     * method will create a new vehicle object
     */
    public Vehicle(String vehicleModel, String vehicleMake, int vehicleYear) {
        this.vehicleModel = vehicleModel;
        this.vehicleMake = vehicleMake;
        this.vehicleYear = vehicleYear;
        this.inUse = false;
        this.creationTimeStamp = now;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    //todo add remaining pieces of information
    public String getVehicleDetails() {

        StringJoiner joiner = new StringJoiner(":");
        joiner.add(getVehicleMake())
                .add(getVehicleModel())
                .add(Integer.toString(getVehicleYear()));
        if (isInUse())
            joiner.add("True");

        joiner.add("False");
        return joiner.toString();
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

    // gets the formatted
    public String getCreationTimeStamp() {
        return dtf.format(creationTimeStamp);
    }

    public LocalDateTime getRawCreationTimeStamp() {
        return creationTimeStamp;
    }

}
