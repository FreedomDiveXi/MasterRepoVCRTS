import java.util.ArrayList;

public class JobOwner extends User {
    private ArrayList<Job> ownedJobs;

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

    public ArrayList<Job> getOwnedJobs() {
        return ownedJobs;
    }
    public String getJobOwnerDetails(){

        StringBuilder str = new StringBuilder();
        str.append("Job Owner Username:"+getUsername() +"\n");
        if(getOwnedJobs().isEmpty()){
            str.append("---USER HAS NO JOBS---\n")
                    .append(listOwnedJobs()).append("======\n");
        }else{
            str.append("---Owned Jobs---\n")
                    .append(listOwnedJobs()).append("======\n");
        }

        return String.valueOf(str);
    }
    private String listOwnedJobs() {
        StringBuilder str = new StringBuilder();

        for (Job job : ownedJobs) {
            str.append("Job Id: ").append(job.getJobID())
                    .append("\n")
                    .append("Job Duration: ").append(job.getJobDurationTime())
                    .append("\n");
        }
        return String.valueOf(str);
    }

}
