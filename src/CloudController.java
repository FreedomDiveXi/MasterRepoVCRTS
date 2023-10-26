import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CloudController {

    // todo ** testing
    // todo ** create methods to be able to create users.
    // todo * verification for job, vehicle, users for their ids
    // todo * create a setter/getter for totalCompletionTime

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
        Job newJob = new Job(jobOwnerName,Integer.parseInt(jobId), Integer.parseInt(jobDurationTime));
        addJobToList(getAvailableJobs(), newJob);
        assignJobToVehicle(newJob);
        return newJob;
    }
    // case where we have a deadline
    public Job createJob(String jobOwnerName, String jobId, String jobDurationTime, String jobDeadline){
        Job newJob = new Job(jobOwnerName,Integer.parseInt(jobId), Integer.parseInt(jobDurationTime), jobDeadline);
        addJobToList(getAvailableJobs(), newJob);
        assignJobToVehicle(newJob);
        return newJob;
    }

    //creates a vehicle
    public Vehicle createVehicle(String vehicleOwner, String vehicleId,String model, String make, String year){
        // similar to the
        Vehicle newVehicle = new Vehicle(vehicleOwner, Integer.parseInt(vehicleId), make, model, Integer.parseInt(year));
        // since a vehicle has been created we want it to be stored in the available and in all vehicles
        // its just nice to have just in case we need to reference all the vehicle regardless of status
        addVehicleToList(getAllVehicles(), newVehicle);
        addVehicleToList(getAvailableVehicles(), newVehicle);
        return newVehicle;
    }

    // on job creation will assign the vehicles to a job
    public void assignJobToVehicle(Job job) {
        int numVehicles = generateRedundancy(); // will generate a # 0 - 3 based on available vehicles

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
    public  ArrayList<String> startProcessing() {
        // first migrate the available vehicles to the active job list
        startJobMigration();
        // once everything is on the list and updated we process
        ArrayList <String> jobProcessData = new ArrayList<>();
        while(!getActiveJobs().isEmpty()){
            Job currentJob= getActiveJobs().remove();
            String data = formatActiveJobData(currentJob);
            jobProcessData.add(data);

            addJobToList(getCompletedJobs(),currentJob);
            releaseVehicles(currentJob);
        }
        jobProcessData.add("--------\nTotal time to execute all jobs: " + totalCompletionTime + " hours");
        totalCompletionTime = 0;

        return jobProcessData;
    }

    // migrates the jobs from the availble job list onto the active job list updating each jobs execution time based
    // on jobs ahead of them in queue
    private void startJobMigration(){
        while(isJobsPresent()){
            Job currentAvailableJob = getAvailableJobs().remove();
            totalCompletionTime += currentAvailableJob.getJobDurationTime();
            currentAvailableJob.setExecutionTime(totalCompletionTime);
            addJobToList(getActiveJobs(),currentAvailableJob);
        }
    }

    // returns a string of the jobs current data.
    private String formatActiveJobData(Job job){
        String jobData = """
            ==============
            Job Owner Name: %s
            Job ID: %s
            Job Duration: %s hours
        """.formatted(job.getJobOwnerName(), job.getJobID(), job.getJobDurationTime());

        if (!job.getJobDeadline().isEmpty()) {
            jobData += "\nJob Deadline: " + job.getJobDeadline();
        }

        jobData += "\nTime executed: " + job.getJobExecutionTime() + " hours";
        return jobData;
    }

    private void releaseVehicles(Job job){
        while(!job.getAssignedVehicles().isEmpty()){
            int lastElement = job.getAssignedVehicles().size()-1;
            Vehicle temp = job.getAssignedVehicles().remove(lastElement);

            removeVehicleFromList(getInUseVehicles(), temp); // removes vehicle from in InUseList
            temp.removeAssignedJob();
            addVehicleToList(getAvailableVehicles(), temp);
        }
    }

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

    // responsible for generating a random number 0-3 based on # of availableVehicles
    public int generateRedundancy() {
        if(availableVehicles.isEmpty())
            return 0;
        if(availableVehicles.size() < 3)
            return 1;
        return (int)Math.floor(Math.random() * (3-1 + 1 ) + 1);
    }

    public Queue<Job> getAvailableJobs() {
        return availableJobs;
    }
    public ArrayList<Vehicle> getAvailableVehicles(){
        return availableVehicles;
    }
    public Queue<Job> getActiveJobs() {
        return activeJobs;
    }
    public ArrayList<Vehicle> getInUseVehicles(){
        return inUseVehicles;
    }
    public ArrayList<Job> getCompletedJobs() {
        return completedJobs;
    }
    public ArrayList<Vehicle> getAllVehicles() {
        return allVehicles;
    }
    // will return true if there are some jobs in the available job list ready to process
    public boolean isJobsPresent(){
        return !getAvailableJobs().isEmpty();
    }

    // user stuff
    //todo ***
    public void seeProgressOfJobs() {
    }

    //todo ***
    public void seeAllDataBases() {
    }

    //todo **
    public void updateDatabaseToServer() {
    }

    // todo leave till further notice
    public void updateCheckpoint() {
        // based on the time. 10
        // 10 secs after 5 seconds we run this method
        // this means this method will run at the halfway mark to keep things simple.
    }
}