import java.util.ArrayList;

public class Job {
    private String clientID;
    private int jobID;
    private int jobDurationTime;
    private String jobDeadline; // date
    private int executionTime;
    private String timeCreated;
    private String jobCompletion;

    private ArrayList<Vehicle> assignedVehicles;

    /**
     * if no deadline is provided will use this constructor method
     * somethingg
     *
     * @param jobID
     * @param jobDurationTime
     */
    public Job(String clientID, int jobID, int jobDurationTime, String creationTime){
        this.clientID =  clientID;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.executionTime = 0;
        timeCreated = creationTime;
        jobCompletion = "false";
        assignedVehicles = new ArrayList<>();
    }

    /**
     * overloaded method to provide an optional case in which we can create a
     * job with a deadline
     *
     * @param jobID
     * @param jobDurationTime
     */
    public Job(String clientID, int jobID, int jobDurationTime, String jobDeadline, String creationTime) {
        this.clientID =  clientID;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.jobDeadline = jobDeadline;
        this.executionTime = 0;
        timeCreated = creationTime;
        jobCompletion = "false";
        assignedVehicles = new ArrayList<>();
    }

    // Getter and Setter methods
public String getClientID() {
    return clientID;
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

public String isJobCompletion() {
    return jobCompletion;
}

public void setJobCompletion(String jobCompletion) {
    this.jobCompletion = jobCompletion;
}

public int getJobExecutionTime(){
    return executionTime;
}

public void setExecutionTime(int executeTime){
    this.executionTime = executeTime;
}

public void addAssignedVehicle(Vehicle vehicle){
        assignedVehicles.add(vehicle);
}
public void removeAssignedVehicle(Vehicle vehicle){
        assignedVehicles.removeIf(n -> n.getVehicleID() == vehicle.getVehicleID());
}
public ArrayList<Vehicle> getAssignedVehicles() {
    return assignedVehicles;
}

public String getTimeCreated(){
        return timeCreated;
}
}

