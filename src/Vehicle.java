public class Vehicle {
    private int vehicleId;
    private String ownerID;
    private String make;
    private String model;
    private int year;
    private String timeCreated;
    private Job assignedJob = null;

    public Vehicle(String ownerID, int vehicleId, String make, String model, int year, String creationTime) {
        this.vehicleId = vehicleId;
        this.ownerID = ownerID;
        this.make = make;
        this.model = model;
        this.year = year;
        timeCreated = creationTime;
    }

    public String getOwnerID(){
        return ownerID;
    }

	public int getVehicleID() {
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
    public String getTimeCreated(){
        return timeCreated;
    }

}
