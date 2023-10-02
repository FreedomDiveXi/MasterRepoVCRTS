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
    
    @Override
    public String toString() {
        return "Job{" +
                "jobID=" + jobID +
                ", jobDurationTime=" + jobDurationTime +
                ", jobDeadline=" + (jobDeadline != null ? jobDeadline : "No deadline") +
                ", completed=" + completed +
                '}';
    }
}
