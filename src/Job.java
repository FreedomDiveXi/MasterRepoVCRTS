import java.util.ArrayList;

public class Job {
    private String jobOwnerName;
    private int jobID;
    private int jobDurationTime;
    private String jobDeadline; // date
    private int executionTime;
    private boolean jobCompletion;

    private ArrayList<Vehicle> assignedVehicles;

    /**
     * if no deadline is provided will use this constructor method
     * somethingg
     *
     * @param jobID
     * @param jobDurationTime
     */
    public Job(String jobOwnerName, int jobID, int jobDurationTime) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.executionTime = 0;
        jobCompletion = false;
        assignedVehicles = new ArrayList<>();
    }

    /**
     * overloaded method to provide an optional case in which we can create a
     * job with a deadline
     *
     * @param jobID
     * @param jobDurationTime
     */
    public Job(String jobOwnerName, int jobID, int jobDurationTime, String jobDeadline) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.jobDeadline = jobDeadline;
        this.executionTime = 0;
        jobCompletion = false;
        assignedVehicles = new ArrayList<>();
    }

    // Getter and Setter methods
public String getJobOwnerName() {
    return jobOwnerName;
}

public int getJobID() {
    return jobID;
}

public int getJobDurationTime() {
    return jobDurationTime;
}

public String getJobDeadline() {
    return jobDeadline;
}

public boolean isJobCompletion() {
    return jobCompletion;
}

public void setJobCompletion(boolean jobCompletion) {
    this.jobCompletion = jobCompletion;
}

public int getJobExecutionTime(){
    return executionTime;
}

public void setExecutionTime(int executeTime){
    this.executionTime += executeTime;
}

public void addAssignedVehicle(Vehicle vehicle){
        assignedVehicles.add(vehicle);
}
public void removeAssignedVehicle(Vehicle vehicle){
        assignedVehicles.removeIf(n -> n.getVehicleId() == vehicle.getVehicleId());
}
public ArrayList<Vehicle> getAssignedVehicles() {
    return assignedVehicles;
}
}
