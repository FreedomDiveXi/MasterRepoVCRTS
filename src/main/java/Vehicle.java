import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Vehicle {
    private String vehicleOwnerName;
    private String vehicleModel;
    private String vehicleMake;
    private int vehicleYear;
    private LocalTime vehicleArrivalTime;
    private LocalTime vehicleESTDepartureTime;

    public Vehicle(String vehicleOwnerName, String vehicleModel, String vehicleMake, int vehicleYear, LocalTime vehicleArrivalTime, LocalTime vehicleESTDepartureTime) {
        this.vehicleOwnerName = vehicleOwnerName;
        this.vehicleModel = vehicleModel;
        this.vehicleMake = vehicleMake;
        this.vehicleYear = vehicleYear;
        this.vehicleArrivalTime = vehicleArrivalTime;
        this.vehicleESTDepartureTime = vehicleESTDepartureTime;
    }
}
