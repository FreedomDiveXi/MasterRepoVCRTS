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

    private static ArrayList<Integer> currentVehicleIds = new ArrayList<>();

    public Vehicle(String vehicleOwner, int vehicleId, String make, String model, int year) {
        if (isUniqueId(vehicleId)) {
            this.vehicleId = vehicleId;
            currentVehicleIds.add(vehicleId);
        } else {
            throw new IllegalArgumentException("Duplicate vehicleId, please input a number other than " + vehicleId);
        }
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

    private static boolean isUniqueId(int vehicleId) {
        return !currentVehicleIds.contains(vehicleId);
        // true - doesn't exist yet, false, exists
    }
}
