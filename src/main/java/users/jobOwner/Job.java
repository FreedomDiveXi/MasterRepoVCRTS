package users.jobPerson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class Job {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDateTime now = LocalDateTime.now();
    private String jobId;
    private LocalDateTime creationTimeStamp;
    private String jobDurationTime;
    private String jobDeadline;
    private boolean completed;

    /**
     * for now based on the specifications a job can only submit the job
     * duration and the job deadline. for now a simple string is more than
     * enough.
     * On every instance it will generate a new unique ID.
     * Will time stamp the object when it's created onto the actual job item
     * itself so that we know when the job is created.
     * sets completed to default.
     */
    public Job(String jobDuration, String deadline) {
        this.jobId = getUniqueId();
        this.jobDurationTime = jobDuration;
        this.jobDeadline = deadline;
        this.completed = false;
        this.creationTimeStamp = now;

    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    public String getJobId() {
        return jobId;
    }

    // gets the date in which
    public String getCreationTimeStamp() {
        return dtf.format(creationTimeStamp);
    }

    public LocalDateTime getRawCreationTimeStamp() {
        return creationTimeStamp;
    }

    public String getJobDurationTime() {
        return jobDurationTime;
    }

    public String getJobDeadline() {
        return jobDeadline;
    }

    // returns the current status of a job
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
