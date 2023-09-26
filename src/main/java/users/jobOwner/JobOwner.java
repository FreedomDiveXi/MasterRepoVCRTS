package users.jobOwner;

import java.util.ArrayList;

public class JobOwner {
    private String jobOwnerId;
    private String jobPassword;
    private ArrayList<Job> jobList;

    public JobOwner(String jobOwnerId, String jobPassword) {
        this.jobOwnerId = jobOwnerId;
        this.jobPassword = jobPassword;

    }

    public String getJobOwnerId() { return jobOwnerId; }

    public String getJobPassword() { return jobPassword; }

    public ArrayList<Job> getJobList() { return jobList; }
}
