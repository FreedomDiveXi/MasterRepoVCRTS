import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Job {
    private String jobOwnerName;
    private int jobID;
    private int jobDurationTime;
    private int executionTime;
    private String jobDeadline; // date
    private boolean jobCompletion;

    /**
     * if no deadline is provided will use this constructor method
     * somethingg
     *
     * @param jobOwnerName
     * @param jobID
     * @param jobDurationTime
     * @param jobCompletion
     */
    public Job(int jobID, int jobDurationTime) {
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.executionTime = 0;
        jobCompletion = false;
    }

    /**
     * overloaded method to provide an optional case in which we can create a
     * job with a deadline
     *
     * @param jobOwnerName
     * @param jobID
     * @param jobDurationTime
     * @param jobDeadline
     * @param jobCompletion
     */
    public Job(int jobID, int jobDurationTime, String deadline) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.executionTime = 0;

        jobCompletion = false;

    }

    // Getter and Setter methods
public String getJobOwnerName() {
    return jobOwnerName;
}

public void setJobOwnerName(String jobOwnerName) {
    this.jobOwnerName = jobOwnerName;
}

public int getJobID() {
    return jobID;
}

public void setJobID(int jobID) {
    this.jobID = jobID;
}

public int getJobDurationTime() {
    return jobDurationTime;
}

public void setJobDurationTime(int jobDurationTime) {
    this.jobDurationTime = jobDurationTime;
}

public String getJobDeadline() {
    return jobDeadline;
}

public void setJobDeadline(String jobDeadline) {
    this.jobDeadline = jobDeadline;
}

public boolean isJobCompletion() {
    return jobCompletion;
}

public void setJobCompletion(boolean jobCompletion) {
    this.jobCompletion = jobCompletion;
}

public int getjobExecutionTime(){
    return executionTime;
}

public void setExecutionTime(int executeTime){
    this.executionTime += executeTime;
}

}
