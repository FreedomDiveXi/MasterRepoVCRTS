package users.jobOwner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.UUID;

public class JobOwner {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDateTime now = LocalDateTime.now();
    private LocalDateTime creationTimeStamp;
    private String userUniqueId;
    private String jobOwnerUsername;
    private String jobOwnerPassword;
    private ArrayList<Job> jobList;
    private ArrayList<Job> jobHistory;

    public JobOwner(String jobOwnerUsername, String jobOwnerPassword) {
        creationTimeStamp = now;
        userUniqueId = getUniqueId();
        this.jobOwnerUsername = jobOwnerUsername;
        this.jobOwnerPassword = jobOwnerPassword;
        this.jobList = new ArrayList<Job>();
        this.jobHistory = new ArrayList<Job>();
    }

    public String getJobOwnerUsername() {
        return jobOwnerUsername;
    }

    public String getJobOwnerPassword() {
        return jobOwnerPassword;
    }

    public ArrayList<Job> getJobList() {
        return jobList;
    }

    public ArrayList<Job> getJobHistory() {
        return jobHistory;
    }

    // formats the local date time into a string
    public String getCreationTimeStamp() {
        return dtf.format(creationTimeStamp);
    }

    // meant for later instances in which we want to have to work with the
    // actual local data time type
    public LocalDateTime getRawCreationTimeStamp() {
        return creationTimeStamp;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    public String getUserDetails() {
        StringJoiner joiner = new StringJoiner("||");
        joiner.add(getCreationTimeStamp())
                .add(getUniqueId())
                .add(getJobOwnerUsername())
                .add(getJobOwnerPassword())
                .add(getJobList().toString())
                .add(getJobHistory().toString());


        return joiner.toString();

    }
}
