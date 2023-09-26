package users.admin;

public class Client {
    private String ownerID;
    private String jobDuration;
    private String jobDeadline;

    // Constructor 
    public Client(String ownerID, String jobDuration, String jobDeadline) {
        this.ownerID = ownerID;
        this.jobDuration = jobDuration;
        this.jobDeadline = jobDeadline;
    }

    // Getter for ownerID
    public String getOwnerID() {
        return ownerID;
    }

    // Setter for ownerID
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    // Getter for jobDuration
    public String getJobDuration() {
        return jobDuration;
    }

    // Setter for jobDuration
    public void setJobDuration(String jobDuration) {
        this.jobDuration = jobDuration;
    }

    // Getter for jobDeadline
    public String getJobDeadline() {
        return jobDeadline;
    }

    // Setter for jobDeadline
    public void setJobDeadline(String jobDeadline) {
        this.jobDeadline = jobDeadline;
    }

}
