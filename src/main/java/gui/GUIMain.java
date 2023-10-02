package gui;

import controller.VehicleOwnerController;
import users.jobOwner.Job;
import users.jobOwner.JobOwner;
import users.vehicleOwner.Vehicle;
import users.vehicleOwner.VehicleOwner;

import javax.swing.*;
import java.io.IOException;

public class GUIMain {
    private static GUIMain instance;
    private final JFrame mainWindow;
    private VehicleOwner vehicleOwner;
    private JobOwner jobOwner;
    private Vehicle vehicle;
    private Job job;

    private VehicleOwnerController vehicleController = VehicleOwnerController.getInstance();
    // assumes that the job person has the same set up as the vehicle controller in that we're using a singleton pattern
//    private JobOwnerController jobController = JobOwnerController.getinstance();


    /**
     * singleton pattern
     * ensures we only have access to one Jframe at a time
     */
    private GUIMain() throws IOException {
        mainWindow = new JFrame();
        mainWindow.setContentPane(new StartPage().mainFrame);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public static GUIMain getInstance() throws IOException {
        if (instance == null)
            instance = new GUIMain();
        return instance;
    }

    // method that creates a new vehicle user
    // assumes that when we trigger this will create a new user.
    public void registerNewVehicleUser(String userName, String password) {
//        vehicleOwner = vehicleController.createUser(userName, password);
    }

    // method that creates a new vehicle object.
    // assumes that when we trigger this, this will run a new vehicle
    public void registerNewVehicle(VehicleOwner vehicleOwner, String model, String maker, String year) {
        // assumes that the vehicle controller has a method called create vehicle
//        vehicle  = vehicleController.createVehicle(vehicleOwner,model, maker, year);
    }

    // method that will create a new job user
    public void registerNewJobUser(String userName, String password) {
        // assumes job has a method that can create a job user
//        jobOwner = jobController.createUser(userName,password);

    }

    // method that will create a new job
    public void registerNewJob(String deadline, String jobDescription) {
        // assumes job controller has a method that can create a job
//        job = jobController.createJob(deadline,jobDescription);
    }

    /**
     * J frame methods
     */
    // allows to set a new view in the frame.
    public void setContentPane(JPanel panel) {
        mainWindow.setContentPane(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }
}