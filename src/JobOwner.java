import java.util.ArrayList;

public class JobOwner extends User {
    private ArrayList<Job> ownedJobs;
    private String companyName;
    private String contactNumber;

    public JobOwner(String username, String password) {
        super(username, password);
        this.ownedJobs = new ArrayList<>();
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
       public List<Job> getOwnedJobs() {
        return new ArrayList<>(ownedJobs);
    }
    
}
