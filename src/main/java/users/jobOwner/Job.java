package users.jobOwner;

import java.time.LocalTime;
import java.time.LocalDate;

public class Job {

    private int jobID;
    private LocalTime jobDurationTime;
    private LocalDate jobDeadline;
    private boolean completed;

    /**
     * if no deadline is provided will use this constructor method
     *
     * @param jobID
     * @param jobDurationTime
     */
    public Job( int jobID, LocalTime jobDurationTime) {

        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.completed = false;
    }

    /**
     * overloaded method to provide an optional case in which we can create a
     * job with a deadline
     *
     * @param jobID
     * @param jobDurationTime
     * @param jobDeadline
     */
    public Job(int jobID, LocalTime jobDurationTime, LocalDate jobDeadline) {
        this.jobID = jobID;
        this.jobDurationTime = jobDurationTime;
        this.jobDeadline = jobDeadline;
        this.completed = false;
    }

    public int getJobID() {
        return jobID;
    }
    public LocalTime getJobDurationTime() {
        return jobDurationTime;
    }
    public LocalDate getJobDeadline() {
        return jobDeadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
