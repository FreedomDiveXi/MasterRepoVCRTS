import java.util.ArrayList;

// todo remove the unique id from this file, as this should be handled by the
//  controller

public class Vehicle {
    private int vehicleId;
    private String vehicleOwner;
    private String make;
    private String model;
    private int year;
    private Job assignedJob = null;

    public Vehicle(String vehicleOwner, int vehicleId, String make, String model, int year) {
        this.vehicleId = vehicleId;
        this.vehicleOwner = vehicleOwner;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getVehicleOwner(){
        return vehicleOwner;
    }

	public int getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Job getAssignedJob(){
        return assignedJob;
    }
    public void setAssignedJob(Job job){
        assignedJob = job;
    }

    public void removeAssignedJob(){
        assignedJob = null;
    }

}
