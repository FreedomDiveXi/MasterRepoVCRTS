import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class CloudController {
    /**
     * the vehicle controller is responsible for the processing logic of the jobs and vehicles.
     * it's responsible for the creation of all users and items (job and vehicle respectively).
     * it's responsible for connecting to the server to update the users or items database respectively for future
     * milestones.
     */

    // todo ** testing

    private ArrayList<Vehicle> availableVehicles;
    private ArrayList<Vehicle> inUseVehicles;
    private Queue<Job> availableJobs;
    private Queue<Job> activeJobs;
    private ArrayList<Job> completedJobs;
    private ArrayList<Vehicle> allVehicles;
    private final UserList allUsers;
    private int totalCompletionTime;
    private JobOwner currentJobOwner;
    private VehicleOwner currentVehicleOwner;

    public CloudController(){
        availableVehicles = new ArrayList<Vehicle>();
        inUseVehicles = new ArrayList<Vehicle>();
        availableJobs = new LinkedList<Job>();
        activeJobs = new LinkedList<Job>();

        completedJobs = new ArrayList<Job>();
        allVehicles = new ArrayList<Vehicle>();
        allUsers = new UserList();

        currentJobOwner = null;
        currentVehicleOwner = null;

        // used to store the total time it took to process jobs
        totalCompletionTime = 0;
        init();
    }

    /**
     * Initializes controller with registered data on startup
     * todo instead of manually creating vehicles/jobs/owners have it so it reads the file and creates them accordingly
     */
    private void init(){
        VehicleOwner sampleVehicleOwner = createVehicleOwner("mark", "asdfjk1kjhsdf12323");
        createVehicle("asdoc", "1", "civic", "honda", "2023");
        createVehicle("asdoc", "2", "pilot", "honda", "2023");
        createVehicle("asdoc", "3", "cr-v", "honda", "2023");
        createVehicle("asdoc", "4", "accord", "honda", "2023");
        createVehicle("asdoc", "5", "hr-v", "honda", "2023");
        createVehicle("asdoc", "6", "civic type-r", "honda", "2023");
        createVehicle("asdoc", "7", "model Y", "tesla", "2023");
        createVehicle("asdoc", "8", "model S", "tesla", "2023");
        createVehicle("asdoc", "9", "cybertruck", "tesla", "2023");
    }

    /**
     * this constructor will create a job owner. Each time we create a job owner we store a reference of the job owner
     * and append the owner to the user list
     * @param username String value provided by gui
     * @param password String value provided by gui
     * @return returns the created job owner
     */
    public JobOwner createJobOwner(String username, String password){
        JobOwner temp = new JobOwner(username,password);
        setCurrentJobOwner(temp);
        allUsers.getUsers().add(temp); // adds user's to the user list
        return temp;
    }

    /**
     * this constructor will create a vehicle owner. Each time we create a vehicle owner
     * we store a reference of the vehicle owner and append the owner to the user list
     * @param username String value provided by gui
     * @param password String value provided by gui
     * @return returns the created vehicle owner
     */
    public VehicleOwner createVehicleOwner(String username, String password){
        VehicleOwner temp = new VehicleOwner(username,password);
        setCurrentVehicleOwner(temp);
        allUsers.getUsers().add(temp); // adds user's to the user list
        return temp;
    }

    /**
     * Creates and returns the newly created job without a deadline.
     * On creation, we append job to available job list, assign vehicles to job and append the job to the current
     * job owner
     * @param clientId String value provided by gui
     * @param jobId String value provided by gui
     * @param jobDurationTime String value provided by gui
     * @return returns the created job without a deadline.
     */
    public Job createJob(String clientId, String jobId, String jobDurationTime){
        Job newJob = new Job(clientId,Integer.parseInt(jobId), Integer.parseInt(jobDurationTime));
        addJobToList(getAvailableJobs(), newJob);
        assignJobToVehicle(newJob);

        getCurrentJobOwner().addJob(newJob); // add the job to the job user
        return newJob;
    }

    /**
     * Creates and returns the newly created job with a deadline.
     * On creation, we append job to available job list, assign vehicles to job and append the job to the current
     * job owner
     * @param clientId String value provided by gui
     * @param jobId String value provided by gui
     * @param jobDurationTime String value provided by gui
     * @return returns the created job with a deadline.
     */
    public Job createJob(String clientId, String jobId, String jobDurationTime, String jobDeadline){
        Job newJob = new Job(clientId,Integer.parseInt(jobId), Integer.parseInt(jobDurationTime), jobDeadline);
        addJobToList(getAvailableJobs(), newJob);
        assignJobToVehicle(newJob);

        getCurrentJobOwner().addJob(newJob); // add the job to the job user
        return newJob;
    }

    /**
     * Creates and returns the newly created vehicle.
     * On creation, appends created vehicle to the all vehicle list, appends the vehicle to the available vehicle list.
     * It appends the new vehicle onto the vehicle owners vehicle list.
     * @param ownerId String provided by gui
     * @param vehicleId String provided by gui MUST BE A NUMBER
     * @param model String provided by gui
     * @param make String provided by gui
     * @param year String provided by gui
     * @return returns the newly created vehicle MUST BE A NUMBER
     */
    public Vehicle createVehicle(String ownerId, String vehicleId,String model, String make, String year){
        // similar to the
        Vehicle newVehicle = new Vehicle(ownerId, Integer.parseInt(vehicleId), make, model, Integer.parseInt(year));

        addVehicleToList(getAllVehicles(), newVehicle);
        addVehicleToList(getAvailableVehicles(), newVehicle);

        getCurrentVehicleOwner().addVehicleToVehicleUserList(newVehicle); // appends the vehicle to the current user
        return newVehicle;
    }

    /**
     * Assigns a job to vehicle(s). The number of vehicles that go to a job will be determined by a redundancy that the
     * system creates. This method is run on job creation automatically.
     * If there are no available vehicles it does not assign a vehicle to the job.
     * Every time we assign a job to vehicle, we remove that vehicle from the available vehicle list and onto the in
     * use vehicle list. It also copies a image of the job onto the vehicle itself. The job also stores an image of the
     * assigned vehicle
     * @param job Job object provided by method calling
     */
    private void assignJobToVehicle(Job job) {
        int numVehicles = generateRedundancy();

        while(numVehicles > 0){
            if(getAvailableVehicles().isEmpty())
                break;
            int lastElement = availableVehicles.size()-1;
            Vehicle assignedVehicle = availableVehicles.remove(lastElement); // aux vehicle

            assignedVehicle.setAssignedJob(job);
            job.addAssignedVehicle(assignedVehicle);

            removeVehicleFromList(getAvailableVehicles(), assignedVehicle); // when vehicle assigned removed from available list
            addVehicleToList(getInUseVehicles(), assignedVehicle);
            numVehicles--;
        }
    }

    /**
     * This will begin processing all the jobs within the available job list, and returns a list of strings containing
     * each jobs execution information.
     * It first starts the job migration, which moves the available jobs onto the active job list. Then the active job
     * list will begin processing removing each job from the list at a time. Each time we remove a job, we move the job
     * into the completed job list, and we release/remove the vehicle(s) associated with the job. Resets total completion
     * time when done processing jobs in active job list.
     * @return returns a processed job string.
     */

    public String startProcessing() {
        // first migrate the available vehicles to the active job list
        startJobMigration();
        // once everything is on the list and updated we process
        StringBuilder str = new StringBuilder();
        while(!getActiveJobs().isEmpty()){
            Job currentJob= getActiveJobs().remove();

            str.append(formatActiveJobData(currentJob));

            addJobToList(getCompletedJobs(),currentJob);
            releaseVehicles(currentJob);
        }
        if(getTotalCompletionTime() == 0){
            str.append("---NO JOBS HAVE BEEN SUBMITTED---");
        }else{
            str.append("<br/>---Total Time Execution: ")
                    .append(getTotalCompletionTime())
                    .append("---<br/>");
        }

        return String.valueOf(str);
    }

    /**
     * Migrates the jobs from available job list to the active job list so that it can be processed.
     * Only jobs associated with a vehicle will be migrated onto the active job list.
     * If the current job happens to have no vehicles associated to it, we try and add a vehicle. If it still doesn't
     * have a vehicle we return the job back onto the available job list
     */
    private void startJobMigration(){
        while(isJobsPresent()){
            Job currentAvailableJob = getAvailableJobs().remove();

            // if there are any vehicles adds any if possible
            if(currentAvailableJob.getAssignedVehicles().isEmpty()){
                assignJobToVehicle(currentAvailableJob);
            }

            // if it couldn't add a vehicle it goes back onto the available job list
            if(currentAvailableJob.getAssignedVehicles().isEmpty()){
                addJobToList(getAvailableJobs(), currentAvailableJob);
                break;
            }

            setTotalCompletionTime(currentAvailableJob.getJobDurationTime());

            currentAvailableJob.setExecutionTime(getTotalCompletionTime());
            addJobToList(getActiveJobs(),currentAvailableJob);
        }
    }

    /**
     * Release/removes the vehicles associated to a job. On removal, will remove the vehicle from the in use vehicle
     * list, removes the assigned job from the vehicle, then moves the vehicle back onto the available vehicle
     * list
     * @param job job object
     */
    private void releaseVehicles(Job job){
        while(!job.getAssignedVehicles().isEmpty()){
            int lastElement = job.getAssignedVehicles().size()-1;
            Vehicle assignedVehicle = job.getAssignedVehicles().remove(lastElement);

            removeVehicleFromList(getInUseVehicles(), assignedVehicle);
            assignedVehicle.removeAssignedJob();
            addVehicleToList(getAvailableVehicles(), assignedVehicle);
        }
    }

    /**
     * Returns a formatted processed job data. Method is exclusive to the job processing algorithm.
     * @param job Job object passed by method calling.
     * @return  Returns the processed job data in a formatted String
     */
    private String formatActiveJobData(Job job){
        String str = "";
        str += "=========" + "<br/>";
        str += "Job Owner Name : " + job.getJobOwnerName() + "<br/>";
        str += "Job Id: " + job.getJobID() + "<br/>";
        str += "Job Duration: " + job.getJobDurationTime() + "<br/>";

        if(job.getJobDeadline() != null)
            str += "Job Deadline: " + job.getJobDeadline() + "<br/>";

        str += "Time Executed: " + job.getJobExecutionTime() + "<br/>";

        return str;
    }

    /**
     * Prints all the completed jobs.
     * @return A formatted string of the jobs completed
     */
    public String printCompletedJobs(){
        StringBuilder str = new StringBuilder();
        for(Job currentJob: getCompletedJobs()){

            str.append("Job Owner Name: ").append(currentJob.getJobOwnerName()).append("\n");
            str.append("Job Id: ").append(currentJob.getJobID()).append("\n");
            str.append("Job Duration: ")
                    .append(currentJob.getJobDurationTime())
                    .append(" hours").append("\n");

            if(currentJob.getJobDeadline() != null)
                str.append("Job Deadline: ").append(currentJob.getJobDeadline()).append("\n");

            str.append("Time job was completed: ")
                    .append(currentJob.getJobExecutionTime())
                    .append(" hours").append("\n\n");
        }
        return String.valueOf(str);
    }

    /**
     * Prints all the vehicles currently registered
     * @return A formatted string of all the vehicles registered
     */
    public String printAllVehicles(){
        StringBuilder str = new StringBuilder();
        for(Vehicle currentVehicle: getAllVehicles()){

            str.append("Vehicle Owner Name: ").append(currentVehicle.getVehicleOwner()).append("\n");
            str.append("Vehicle Id: ").append(currentVehicle.getVehicleId()).append("\n");
            str.append("Vehicle make: ").append(currentVehicle.getMake()).append("\n");
            str.append("Vehicle model: ").append(currentVehicle.getModel()).append("\n");
            str.append("Vehicle year: ").append(currentVehicle.getYear()).append("\n\n");
        }
        return String.valueOf(str);
    }

    public String printAllUsers(){
        StringBuilder str = new StringBuilder();

        for(User users: allUsers.getUsers()){
            if(users instanceof JobOwner)
                str.append(((JobOwner) users).getJobOwnerDetails());
            if(users instanceof VehicleOwner)
                str.append(((VehicleOwner) users).getVehicleOwnerDetails());
        }

        return String.valueOf(str);
    }

    /**
     * generates the redundancy of the job. Used to know how many vehicles go to a job. Generating a value from
     * 0-2 based on the total amount of vehicles available.
     * @return returns a value 0-2
     */
    private int generateRedundancy() {
        if(availableVehicles.isEmpty())
            return 0;
        if(availableVehicles.size() < 2)
            return 1;
        return (int)Math.floor(Math.random() * (2-1 + 1 ) + 1);
    }

    /**
     * Adds a job to a job queue.
     * @param jobQueue Job queue list
     * @param job Job object.
     */
    private void addJobToList(Queue<Job> jobQueue, Job job){
        jobQueue.add(job);
    }

    /**
     * Adds a job to a job list.
     * @param jobList Job array list
     * @param job Job object.
     */
    private void addJobToList(ArrayList<Job> jobList, Job job){
        jobList.add(job);
    }

    /**
     * adds a vehicle to a list.
     * @param vehicleList vehicle array list
     * @param vehicle vehicle object
     */
    private void addVehicleToList(ArrayList<Vehicle> vehicleList, Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    /**
     * removes a vehicle from a list. It will remove a vehicle based on the vehicles ID.
     * If multiple vehicles have the same ID it will remove them.
     * @param vehicleList passed in vehicle list
     * @param vehicle passed in vehicle object
     */
    private void removeVehicleFromList(ArrayList<Vehicle> vehicleList, Vehicle vehicle){
        vehicleList.removeIf(n-> n.getVehicleId() == vehicle.getVehicleId());
    }

    /**
     * todo leave till further notice
     * a cool idea would that based on the jobOwners total owned jobs, we can have something that says.
     * "50% of your owned jobs have been completed!"
     * and then when all the jobs in their list are done we can show a string that says.
     * "Hooray all your jobs are completed! ".
     */
    private void seeProgressOfJobs() {
    }

    //todo leave till further notice
    private void seeAllDataBases() {
    }

    //todo leave till further notice
    private void updateDatabaseToServer() {
    }

    // todo leave till further notice
    private void updateCheckpoint() {
    }

    // Getters /setters
    public ArrayList<Vehicle> getAvailableVehicles(){
        return availableVehicles;
    }
    public Queue<Job> getAvailableJobs() {
        return availableJobs;
    }
    public ArrayList<Vehicle> getInUseVehicles(){
        return inUseVehicles;
    }
    public Queue<Job> getActiveJobs() {
        return activeJobs;
    }

    public ArrayList<Job> getCompletedJobs() {
        return completedJobs;
    }
    public ArrayList<Vehicle> getAllVehicles() {
        return allVehicles;
    }

    public JobOwner getCurrentJobOwner() {
        return currentJobOwner;
    }
    private void setCurrentJobOwner(JobOwner currentJobOwner) {
        this.currentJobOwner = currentJobOwner;
    }

    public VehicleOwner getCurrentVehicleOwner() {
        return currentVehicleOwner;
    }

    private void setCurrentVehicleOwner(VehicleOwner currentVehicleOwner) {
        this.currentVehicleOwner = currentVehicleOwner;
    }

    public int getTotalCompletionTime() {
        return totalCompletionTime;
    }

    public void setTotalCompletionTime(int totalCompletionTime) {
        this.totalCompletionTime = totalCompletionTime + this.totalCompletionTime;
    }

    public boolean isJobsPresent(){
        return !getAvailableJobs().isEmpty();
    }
}