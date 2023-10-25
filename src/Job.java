package users.jobOwner;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Job {
    private String jobOwnerName;
    private int jobID;
    private LocalTime jobDurationTime;
    private LocalDate jobDeadline;
    private boolean jobCompletion;

    /**
     * if no deadline is provided will use this constructor method
     *
     * @param jobOwnerName
     * @param jobID
     * @param jobDurationTime
     * @param jobCompletion
     */
    public Job(String jobOwnerName, int jobID, LocalTime jobDurationTime, boolean jobCompletion) {
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
    public Job(String jobOwnerName, int jobID, LocalTime jobDurationTime, LocalDate jobDeadline, boolean jobCompletion) {
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

public LocalTime getJobDurationTime() {
    return jobDurationTime;
}

public void setJobDurationTime(LocalTime jobDurationTime) {
    this.jobDurationTime = jobDurationTime;
}

public LocalDate getJobDeadline() {
    return jobDeadline;
}

public void setJobDeadline(LocalDate jobDeadline) {
    this.jobDeadline = jobDeadline;
}

public boolean isJobCompletion() {
    return jobCompletion;
}

public void setJobCompletion(boolean jobCompletion) {
    this.jobCompletion = jobCompletion;
}
}