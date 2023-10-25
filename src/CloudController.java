import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CloudController {

    // controller now does everything

    private ArrayList<Vehicle> allVehicles = new ArrayList<>();
    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    private ArrayList<Vehicle> inUseVehicles = new ArrayList<>();


    // FIFO data structure to store the newly created jobs as they come in
    private Queue<Job> availableJobs = new LinkedList<Job>();
    private Queue<Job> activeJobs = new LinkedList<Job>();

    // as the jobs are completed will store them into a simple list
    // this would go to the server and print it into a text file
    private ArrayList<Job> completedJobs = new ArrayList<Job>();

    int completionTime = 0;

    // returns active vehicles
    public ArrayList<Vehicle> getActiveVehicles() {
        return inUseVehicles;
    }

    // returns jobs in queue waiting to be processed
    public Queue<Job> getAvailableJobs() {
        return availableJobs;
    }

    // returns active jobs
    public Queue<Job> getActiveJobs() {
        return activeJobs;
    }

    public boolean checkJobs(){
        return !getAvailableJobs().isEmpty();
    }

    public Job createJob(String jobId, String jobDuration){
        return new Job(Integer.parseInt(jobId),Integer.parseInt(jobDuration));
    }

    public void addToAvailableList(Job job){
        availableJobs.add(job);
    }

    // starts the jobs all at once.
    public String startProcessing() {
        // first migrate
        while(!availableJobs.isEmpty()){
            if(!activeJobs.isEmpty()){
                // if its not empty we pop the available jobs
                Job aux = availableJobs.remove();

                completionTime += aux.getJobDurationTime();

                aux.setExecutionTime(completionTime);
                
                activeJobs.add(aux);
            }else{
                // if its empty simply add it
                Job aux = availableJobs.remove();
                completionTime += aux.getJobDurationTime();
                aux.setExecutionTime(aux.getJobDurationTime());
                activeJobs.add(aux);
            }
        }
        System.out.println(activeJobs.toString());

        while(!activeJobs.isEmpty()){
            System.out.println(activeJobs.remove().getjobExecutionTime());
        }
        System.out.println(activeJobs.toString());
        return "complete";
    }

    //todo method that returns the job algo processing string

    // responsible for generating a random number 1-3 iff theres enough 
    // availableVehicles 
    public int redundancy() {

        if(availableVehicles.isEmpty())
            return 0;

        if(availableVehicles.size() < 3)
            return 1;

        return (int)Math.floor(Math.random() * (3-1 + 1 ) + 1);
    }

    // todo the method will run inbetween the time it takes to complete the job
    // it will generate a new redundancy and copy the job onto more vehicles
    // if we have a delay while executing we can say at the half way mark
    // when we recruit more vehicle we can shave off 1 sec/hour/whatever
    // per # of vehicles
    public void updateCheckpoint() {
        // based on the time. 10
        // 10 secs after 5 seconds we run this method
        // this means this method will run at the halfway mark to keep things simple.
    }

    //todo
    public void assignJobToVehicle(Job job, Vehicle vehicle) {
        // the vehicle must be able to register a job onto itself.
        // when done, the vehicle will be removed from availableVehicles
    }


    //todo *** may be redudant since only one job will be processed at a time till further notice
    // a method already exists to see what jobs are next up on the list
    public void seeProgressOfJobs() {
    }

    //todo ***
    public void eraseVehicleComputation(Vehicle vehicle) {
        // vehicle should be able to return the job assigned to it so we can
        // both remove it from the vehicle itself, and from the active jobs or
        // available jobs
    }

    //todo ** this will remove the job from some active vehicle and will rassign
    // it to another available vehicle
    public void reassignJob(Vehicle vehicle) {
    }

    // leave till further notice
    //todo ***
    public void seeAllDataBases() {
    }
    //todo **
    public void updateDatabaseToServer() {
    }

}
