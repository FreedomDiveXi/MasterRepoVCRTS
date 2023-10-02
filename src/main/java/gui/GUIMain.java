package gui;

import controller.VehicleOwnerController;
import users.jobOwner.JobOwner;
import users.vehicleOwner.VehicleOwner;

import javax.swing.*;
import java.io.IOException;

public class GUIMain {
    private static GUIMain instance;
    private final JFrame mainWindow;
    private VehicleOwner vehicleOwner;
    private JobOwner jobOwner;


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
    public void registerNewVehicleUser(String userName, String password) {
//        auxVehicleUser = vehicleController.createUser(userName, password);

    }
    // method that creates a new vehicle
    public void registerNewVehicle(VehicleOwner vehicleOwner, String model, String maker, String year) {
//        vehicleController.createNewVehicle(auxVehicleUser, model, maker, Integer.parseInt(year));
    }

    // method that will create a new job user
    public void registerNewJobUser(String userName, String password) {
    }

    // method that will create a new job
    public void registerNewJob() {
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