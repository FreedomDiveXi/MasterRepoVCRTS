package users.jobPerson;

import java.util.ArrayList;

public class JobOwner {
    private String jobOwnerId;
    private String jobPassword;
    private ArrayList<Job> jobList;
    private ArrayList<Job> jobHistory;

    public JobOwner(String jobOwnerId, String jobPassword) {
        this.jobOwnerId = jobOwnerId;
        this.jobPassword = jobPassword;
        this.jobList = new ArrayList<Job>();
        this.jobHistory = new ArrayList<Job>();
    }

    public String getJobOwnerId() {
        return jobOwnerId;
    }

    public String getJobPassword() {
        return jobPassword;
    }

    public ArrayList<Job> getJobList() {
        return jobList;
    }

    public ArrayList<Job> getJobHistory() {
        return jobHistory;
    }


}
