import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CloudController {

    // TODO: 10/25/2023 job and vehicle logic mostly dont there may be a few things here and there not quite flushed out
    //  still need to integrate the users. though users will be managed outside. we only create and assign their things
    //  and are managed elsewhere.

    // controller now does everything
    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    private ArrayList<Vehicle> inUseVehicles = new ArrayList<>();

    // FIFO data structure to store the newly created jobs as they come in
    private Queue<Job> availableJobs = new LinkedList<Job>();
    private Queue<Job> activeJobs = new LinkedList<Job>();

    // nice ways to store all completed and all vehicles regardless of status
    private ArrayList<Job> completedJobs = new ArrayList<Job>();
    private ArrayList<Vehicle> allVehicles = new ArrayList<>();

    // used to store the total time it took to process jobs
    private int totalCompletionTime= 0;

    // case we don't have a deadline
    public Job createJob(String jobOwnerName, String jobId, String jobDurationTime){
        Job aux = new Job(jobOwnerName,Integer.parseInt(jobId), Integer.parseInt(jobDurationTime));
        addJobToList(availableJobs, aux);
        assignJobToVehicle(aux);
        return aux;
    }
    // case where we have a deadline
    public Job createJob(String jobOwnerName, String jobId, String jobDurationTime, String jobDeadline){
        Job aux = new Job(jobOwnerName,Integer.parseInt(jobId), Integer.parseInt(jobDurationTime), jobDeadline);
        addJobToList(availableJobs, aux);
        assignJobToVehicle(aux);
        return aux;
    }

    //creates a vehicle
    public Vehicle createVehicle(String vehicleOwner, String vehicleId,String model, String make, String year){
        // similar to the
        Vehicle aux = new Vehicle(vehicleOwner, Integer.parseInt(vehicleId), make, model, Integer.parseInt(year));
        // since a vehicle has been created we want it to be stored in the available and in all vehicles
        // its just nice to have just in case we need to reference all the vehicle regardless of status
        addVehicleToList(allVehicles, aux);
        addVehicleToList(availableVehicles,aux);
        return aux;
    }

    // on job creation will assign the vehicles to a job
    public void assignJobToVehicle(Job job) {
        int numVehicles = getRedundancy(); // will generate a # 0 - 3 based on available vehicles

        while(numVehicles > 0 ){
            int lastElement = availableVehicles.size()-1;
            Vehicle aux = availableVehicles.remove(lastElement); // aux vehicle

            aux.setAssignedJob(job);
            job.addAssignedVehicle(aux);
            removeVehicleFromList(getAvailableVehicles(), aux); // when vehicle assigned removed from available list
            addVehicleToList(getInUseVehicles(), aux);
            numVehicles--;
        }
    }
    // starts the jobs all at once.
    // todo ploish this logic ideally should return a string with all the details of each job processed with total time completion
    public void startProcessing() {
        // first migrate the available vehicles to the active job list
        while(!availableJobs.isEmpty()){
            Job aux = availableJobs.remove();
            totalCompletionTime += aux.getJobDurationTime();
            aux.setExecutionTime(aux.getJobDurationTime());
            activeJobs.add(aux);
        }
        // once everything is on the list and updated we can just pop each job off the list
        while(!activeJobs.isEmpty()){
            System.out.println(activeJobs.remove().getJobExecutionTime());
        }
        totalCompletionTime = 0;
    }

    //todo method that returns the job algo processing string

    // case we have to add a job to a queue
    public void addJobToList(Queue<Job> jobQueue, Job job){
        jobQueue.add(job);
    }
    // case we have to add a job to a list
    public void addJobToList(ArrayList<Job> jobList, Job job){
        jobList.add(job);
    }

    public void addVehicleToList(ArrayList<Vehicle> vehicleList, Vehicle vehicle){
        vehicleList.add(vehicle);
    }
    // removes a vehicle based on its vehicle Id as its unique and dependent on user input
    public void removeVehicleFromList(ArrayList<Vehicle> vehicleList, Vehicle vehicle){
        vehicleList.removeIf(n-> n.getVehicleId() == vehicle.getVehicleId());
    }

    // responsible for generating a random number 1-3 iff theres enough
    // availableVehicles  this will be used to know how many vehicles will go to each job
    public int getRedundancy() {
        if(availableVehicles.isEmpty())
            return 0;
        if(availableVehicles.size() < 3)
            return 1;
        return (int)Math.floor(Math.random() * (3-1 + 1 ) + 1);
    }


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
    public ArrayList<Vehicle> getAvailableVehicles(){
        return availableVehicles;
    }
    public ArrayList<Vehicle> getInUseVehicles(){
        return getInUseVehicles();
    }


    public boolean checkJobs(){
        return !getAvailableJobs().isEmpty();
    }

    // user stuff

    //todo *** may be redudant since only one job will be processed at a time till further notice
    // a method already exists to see what jobs are next up on the list
    public void seeProgressOfJobs() {
    }

    //todo ** this will remove the job from some active vehicle and will rassign
    // it to another available vehicle
    public void reassignJob(Vehicle vehicle) {
    }

    // leave till further notice
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

    //todo ***
    public void eraseVehicleComputation(Vehicle vehicle) {
        // vehicle should be able to return the job assigned to it so we can
        // both remove it from the vehicle itself, and from the active jobs or
        // available jobs
    }

    //todo ***
    public void seeAllDataBases() {
    }

    //todo **
    public void updateDatabaseToServer() {
    }
}