import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CloudController {

    // this class will be responsible for most of the processing gui logic.
    // one major assumption is that the controller is not responsible for the creation of either
    // respected owners. the controller is only given access to the users information via the gui on submission
    // of jobs and vehicles.

    // todo
    ArrayList<String> vehicleList = new ArrayList<>();

    // this will be replaced to be of type jobs.
    // FIFO data structure to store the newly created jobs as they come in
    Queue<Number> jobPool = new LinkedList<Number>();

    // this will be replaced to be of type jobs
    // as the jobs are completed will store them into a simple list
    ArrayList<String> completedJobs = new ArrayList<>();

    //todo
    public void activeVehicleList() {
    }

    //todo
    public void availableJobs() {
    }

    //todo
    public void activeJobs() {
    }

    //todo
    public void updateCheckpoint() {
    }

    //todo
    public void startJob() {
    }

    //todo responsible to set the # of cars associated to some job to keep things simple it will randomize a
    // number between 1-3. anything < 3, only one car will be assigned to each job.
    public void redundancy() {
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