package controller;

import users.jobOwner.Job;
import users.jobOwner.JobOwner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JobOwnerController {

    private static JobOwnerController instance;
    private final String JOB_OWNER_DATABASE = "JobOwnerDataBase.txt";
    private final String JOB_DATABASE = "JobDataBase.txt";
    private final ArrayList<Job> globalJobList;
    private final ArrayList<JobOwner> globalJobUserList;
    FileWriter writeUser = new FileWriter(JOB_OWNER_DATABASE, true);
    PrintWriter printUser = new PrintWriter(writeUser, true);
    FileWriter writeJob = new FileWriter(JOB_DATABASE, true);
    PrintWriter printJob = new PrintWriter(writeJob, true);

    // singleton pattern to ensure there is only one instance of the vehicle controller.
    private JobOwnerController() throws IOException {
        globalJobList = new ArrayList<Job>();
        globalJobUserList = new ArrayList<JobOwner>();
    }

    public static JobOwnerController getInstance() throws IOException {
        if (instance == null)
            instance = new JobOwnerController();
        return instance;
    }

    /**
     * will create a new user, and will append it to the global user list and
     * will write to job user database.
     */
    public JobOwner createUser(String id, String password) throws IOException {
        JobOwner newUser = new JobOwner(id, password);
        try {
            writeToJobUserFile(addToGlobalList(newUser));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newUser;
    }

    /**
     * will create a new job, and will append it to the global job list.
     * will be assuming that every job only has a job duration and a job
     * deadline
     */

    public Job createNewJob(JobOwner user, String jobDuration,
                            String deadline) {
        Job newJob = new Job(jobDuration, deadline);
        user.getJobList().add(newJob);
        try {
            writeToJobFile(addToGlobalList(newJob));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newJob;
    }

    public void writeToJobUserFile(ArrayList<JobOwner> jobOwners) throws IOException {
        for (JobOwner currentUser : jobOwners) {
            writeUser.write(currentUser.getUserDetails());
            printUser.println();
        }
        // no point in closing since the user can add a new vehicle and want
        // to update the users info with the most up-to-date values
    }

    public void writeToJobFile(ArrayList<Job> jobs) throws IOException {
        for (Job currentVehicle : jobs) {
            writeJob.write(currentVehicle.getJobDetails());
            printJob.println();
        }
        // no point in closing since the user can add a new vehicle and want
        // to update the users info with the most up-to-date values
        updateJobUserFile(globalJobUserList);
    }

    public void updateJobUserFile(ArrayList<JobOwner> list) throws IOException {
        for (JobOwner currentUser : list) {
            writeUser.write(currentUser.getUserDetails());
            printUser.println();
        }
    }

    public void updateJobFile(ArrayList<Job> list) throws IOException {
        for (Job currentJob : list) {
            writeUser.write(currentJob.getJobDetails());
            printUser.println();
        }
    }

    public ArrayList<JobOwner> addToGlobalList(JobOwner item) {
        globalJobUserList.add(item);
        return globalJobUserList;
    }

    public ArrayList<Job> addToGlobalList(Job item) {
        globalJobList.add(item);
        return globalJobList;
    }

    public ArrayList<Job> getGlobalVehicleList() {
        return globalJobList;
    }

    public ArrayList<JobOwner> getGlobalJobUserList() {
        return globalJobUserList;
    }

    // when the prof gives us the algo that adds assigns jobs to vehicle
    // will use this to update the status of a job.
    public void updateStatus(Job jobCompleted) {
        if (!jobCompleted.isCompleted())
            jobCompleted.setCompleted(true);
        jobCompleted.setCompleted((false));
    }

    // todo implement job history post-completion

}
