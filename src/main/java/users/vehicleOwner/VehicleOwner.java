package users.vehiclePerson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.UUID;

public class VehicleOwner {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDateTime now = LocalDateTime.now();
    private LocalDateTime creationTimeStamp;
    private String vehicleOwnerUniqueId;
    private String vehicleOwnerUsername;
    private String vehicleOwnerPassword;
    private ArrayList<Vehicle> vechicleList;

    public VehicleOwner(String vehicleOwnerId, String vehicleOwnerPassword) {
        creationTimeStamp = now;
        vehicleOwnerUniqueId = getUniqueId();
        this.vehicleOwnerUsername = vehicleOwnerId;
        this.vehicleOwnerPassword = vehicleOwnerPassword;
        this.vechicleList = new ArrayList<Vehicle>();
    }

    public String getVehicleOwnerUsername() {
        return vehicleOwnerUsername;
    }

    public String getVehiclePassword() {
        return vehicleOwnerPassword;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vechicleList;
    }

    //todo temp solution can be cleaner
    public String getUserDetails() {

        StringJoiner joiner = new StringJoiner(":");
        joiner.add(getVehicleOwnerUsername())
                .add(getVehiclePassword())
                .add(getVehicleList().toString());

        return joiner.toString();
    }


    // gets the formatted
    public String getCreationTimeStamp() {
        return dtf.format(creationTimeStamp);
    }

    public LocalDateTime getRawCreationTimeStamp() {
        return creationTimeStamp;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }

}

