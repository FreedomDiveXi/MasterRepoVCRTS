package controller;

import users.jobOwner.Job;
import users.jobOwner.JobOwner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Our job objects for now live as a singular entities.
 * The controller is meant to bridge them together.
 * That way we can separate the data as much as possible from function/behavior things (except for essential things).
 * So that way we don't have to directly work with the direct object.
 */

//todo
public class JobOwnerController {

    private static JobOwnerController instance;
    private ArrayList<Job> globalJobList;
    private ArrayList<JobOwner> globalUserList;

    private JobOwnerController() {
    }

    public static JobOwnerController getInstance() {
        if (instance == null)
            instance = new JobOwnerController();
        return instance;
    }

    /**
     * will create a new user, and will append it to the global user list.
     */

    public JobOwner createUser(String id, String password) {
        JobOwner newUser = new JobOwner(id, password);
        addToGlobalList(newUser);
        return newUser;
    }

    /**
     * will create a new job, and will append it to the global job list.
     */
    public Job createNewJob(JobOwner user, int jobID, LocalTime jobDurationTime, LocalDate jobDeadline) {
        Job newJob = new Job(jobID, jobDurationTime, jobDeadline);
        addToGlobalList(newJob);
        user.getJobList().add(newJob);
        return newJob;
    }

    public void addToGlobalList(JobOwner item) {
        globalUserList.add(item);
    }

    public void addToGlobalList(Job item) {
        globalJobList.add(item);
    }

    public void updateStatus(Job jobCompleted) {
        if (!jobCompleted.isCompleted())
            jobCompleted.setCompleted(true);
        jobCompleted.setCompleted((false));
    }

    // note-implement job history post-completion

}
