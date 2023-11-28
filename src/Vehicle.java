import java.util.ArrayList;

// todo remove the unique id from this file, as this should be handled by the
//  controller

public class Vehicle {
    private int vehicleId;
    private String vehicleOwner;
    private String brand;
    private String model;
    private int year;
    private Job assignedJob = null;

    public Vehicle(String vehicleOwner, int vehicleId, String model, String brand, int year) {
        this.vehicleId = vehicleId;
        this.vehicleOwner = vehicleOwner;
        this.model = model;
        this.brand = brand;
        this.year = year;
    }

    public String getVehicleOwner(){
        return vehicleOwner;
    }

	public int getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }
    
    public String getBrand() {
        return brand;
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
