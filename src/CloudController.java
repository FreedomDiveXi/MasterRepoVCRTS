import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CloudController {

    // this class will be responsible for most of the processing gui logic.
    // one major assumption is that the controller is not responsible for the 
    // creation of either respected owners. the controller is only given access
    // to the users information via method calling

    VehicleOwner vehicleOwnerTest;
    JobOwner jobOwnerTest;
    Job jobtest;
    Vehicle vehicletest;

    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    private ArrayList<Vehicle> inUseVehicles = new ArrayList<>();


    // FIFO data structure to store the newly created jobs as they come in
    private Queue<Job> availableJobs = new LinkedList<Job>();
    private Queue<Job> activeJobs = new LinkedList<Job>();

    // as the jobs are completed will store them into a simple list
    private ArrayList<Job> completedJobs = new ArrayList<Job>();

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

    // starts the job thats first in line.
    public Job startJob() {
        // first migrate
        while(!availableJobs.isEmpty()){
            //
            if(!activeJobs.isEmpty()){
                Job aux = activeJobs.remove();
                // property of the aux's time duration will get added to the
                // last jobs time duration
                // 10 15 --20--
                // 10 15 35
                // --20---
                // 20 --10--
                // 20 30

                activeJobs.add(aux);

            }else{
                // if its empty simply add it
                activeJobs.add(activeJobs.remove());
            }
        }
        // once acitve jobs are updated pop the first job in the list
        // begin the work on the first in line job
        // 20 --40 60--
        // 60 120
        // 50
        return activeJobs.remove();
    }

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


    //todo *** may be redudant since only one job will be processed at a time
    // a method already exists to see what jobs are next up on the list
    public void seeProgressOfJob() {
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
