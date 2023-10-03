package users.vehicleOwner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.UUID;

public class VehicleOwner {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDateTime now = LocalDateTime.now();
    private LocalDateTime creationTimeStamp;
    private String userUniqueId;
    private String vehicleOwnerUsername;
    private String vehicleOwnerPassword;
    private ArrayList<Vehicle> vehicleList;

    public VehicleOwner(String vehicleOwnerId, String vehicleOwnerPassword) {
        creationTimeStamp = now;
        userUniqueId = getUniqueId();
        this.vehicleOwnerUsername = vehicleOwnerId;
        this.vehicleOwnerPassword = vehicleOwnerPassword;
        this.vehicleList = new ArrayList<Vehicle>();
    }

    public String getVehicleOwnerUsername() {
        return vehicleOwnerUsername;
    }

    public String getVehicleOwnerPassword() {
        return vehicleOwnerPassword;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    // formats the local date time into a string
    public String getCreationTimeStamp() {
        return dtf.format(creationTimeStamp);
    }

    // meant for later instances in which we want to have to work with the
    // actual local data time type
    public LocalDateTime getRawCreationTimeStamp() {
        return creationTimeStamp;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    public String getUserDetails() {

        StringJoiner joiner = new StringJoiner("||");
        joiner.add(getCreationTimeStamp())
                .add(getUniqueId())
                .add(getVehicleOwnerUsername())
                .add(getVehicleOwnerPassword())
                .add(getVehicleList().toString());

        return joiner.toString();
    }


}

