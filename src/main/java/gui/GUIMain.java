package gui;

import controller.VehicleOwnerController;
import users.vehicleOwner.VehicleOwner;

import javax.swing.*;
import java.io.IOException;

public class GUIMain {
    private static GUIMain instance;
    private final JFrame mainWindow;
    private VehicleOwner auxVehicleUser;
    private VehicleOwnerController vehicleController = VehicleOwnerController.getInstance();
//    private JobOwnerController controller = JobOwnerController.getInstance();


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

    public void registerVehicleUser(String userName, String password) {
        auxVehicleUser = vehicleController.createUser(userName, password);
    }

    public void registerUserVehicle(String model, String maker, String year) {
        vehicleController.createNewVehicle(auxVehicleUser, model, maker, Integer.parseInt(year));
    }

    public void registerNewJobOwner(String userName, String password) {
    }

    public void registerNewJob() {

    }


    // allows to set a new view in the frame.
    public void setContentPane(JPanel panel) {
        mainWindow.setContentPane(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }
}

