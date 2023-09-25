package users.jobOwner;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Job {
    private String jobOwnerName;
    private int jobID;
    private LocalTime jobDurationTime;
    private LocalDate jobDeadline;

    /**
     * if no deadline is provided will use this constructor method
     *
     * @param jobOwnerName
     * @param jobID
     * @param jobDurationTime
     */
    public Job(String jobOwnerName, int jobID, LocalTime jobDurationTime) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;

    }

    /**
     * overloaded method to provide an optional case in which we can create a
     * job with a deadline
     *
     * @param jobOwnerName
     * @param jobID
     * @param jobDurationTime
     * @param jobDeadline
     */
    public Job(String jobOwnerName, int jobID, LocalTime jobDurationTime, LocalDate jobDeadline) {
        this.jobOwnerName = jobOwnerName;
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.jobDeadline = jobDeadline;
    }
}

