import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Job {
    private String jobOwnerName;
    private int jobID;
    private String jobDurationTime;
    private int jobDeadline;
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
    public Job(String jobOwnerName, int jobID, String jobDurationTime, boolean jobCompletion) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
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
    public Job(String jobOwnerName, int jobID, String jobDurationTime, String jobDeadline, boolean jobCompletion) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.jobDeadline = jobDeadline;
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

public String getJobDurationTime() {
    return jobDurationTime;
}

public void setJobDurationTime(String jobDurationTime) {
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
      public String getDescription() {
            return description;
        }
        
    public void setDescription(String description) {
            this.description = description;
        }
}
