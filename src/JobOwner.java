import java.util.ArrayList;

public class JobOwner extends User {
    private ArrayList<Job> ownedJobs;
    private String companyName;
    private String contactNumber;

    public JobOwner(String username, String password, String companyName, String contactNumber) {
        super(username, password);
        this.ownedJobs = new ArrayList<>();
        this.companyName = companyName;
        this.contactNumber = contactNumber;
    }

    public void addJob(Job job) {
        ownedJobs.add(job);
    }

    public boolean removeJob(Job job) {
        return ownedJobs.remove(job);
    }

    public void listAllJobs() {
        for (Job job : ownedJobs) {
            System.out.println(job.getJobID() + ": " + job.getJobOwnerName());
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
