import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CloudController {

    // this class will be responsible for most of the processing gui logic.
    // one major assumption is that the controller is not responsible for the creation of either
    // respected owners. the controller is only given access to the users information via the gui on submission
    // of jobs and vehicles.

    VehicleOwner vehicleOwnerTest;
    JobOwner jobOwnerTest;
    Job jobtest;
    Vehicle vehicletest;

    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    private ArrayList<Vehicle> inUseVehicles = new ArrayList<>();


    // this will be replaced to be of type jobs.
    // FIFO data structure to store the newly created jobs as they come in
    private Queue<Job> availableJobs = new LinkedList<Job>();
    private Queue<Job> activeJobs = new LinkedList<Job>();

    // this will be replaced to be of type jobs
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
                activeJobs.add(activeJobs.remove());
            }
        }
        // begin the work on the first in line job
        // 20 --40 60--
        // 60 120
        // 50
        return activeJobs.remove();
    }

    //todo responsible to set the # of cars associated to some job to keep things simple it will randomize a
    // number between 1-3. anything < 3, only one car will be assigned to each job.
    public int redundancy() {
        return (int)Math.floor(Math.random() * (3-1 + 1 ) + 1);
    }

    //todo
    public void updateCheckpoint() {
        // based on the time. 10
        // 10 secs after 5 seconds we run this method
        // this means this method will run at the halfway mark to keep things simple.
    }

    //todo
    public void assignJobToVehicle() {
    }

    //todo ***
    public void seeAllDataBases() {
    }

    //todo ***
    public void seeProgressOfJob() {
    }

    //todo ***
    public void eraseVehicleComputation() {
    }

    //todo **
    public void reassignJob() {
    }

    //todo **
    public void updateDatabaseToServer() {
    }

}