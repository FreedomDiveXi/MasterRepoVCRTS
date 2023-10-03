package gui;

import controller.JobOwnerController;
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
    private final VehicleOwnerController vehicleController = VehicleOwnerController.getInstance();
    private final JobOwnerController jobController = JobOwnerController.getInstance();
    private VehicleOwner vehicleUser;
    private JobOwner jobUser;
    private Vehicle vehicle;
    private Job job;


    /**
     * singleton pattern
     * ensures we only have access to one Jframe at a time
     */
    private GUIMain() throws IOException {
        mainWindow = new JFrame();
        mainWindow.setContentPane(new StartPage().mainFrame);
        mainWindow.setSize(500, 500);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }

    public static GUIMain getInstance() throws IOException {
        if (instance == null)
            instance = new GUIMain();
        return instance;
    }

    // method that creates a new vehicle user
    public void registerNewVehicleUser(String userName, String password) throws IOException {

        try {
            vehicleUser = vehicleController.createUser(userName, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // method that creates a new vehicle object.
    public void registerNewVehicle(String model,
                                   String maker, String year) throws IOException {
        try {
            vehicle = vehicleController.createNewVehicle(vehicleUser, model, maker, Integer.parseInt(year));
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    // method that will create a new job user
    public void registerNewJobUser(String userName, String password) throws IOException {
        try {
            jobUser = jobController.createUser(userName, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // method that will create a new job
    public void registerNewJob(String deadline, String jobDescription) {
        job = jobController.createNewJob(jobUser, deadline, jobDescription);
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