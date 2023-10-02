package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddVehicle {
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JLabel vehicleModelLabel;
    private JTextField vehicleTextField;
    private JLabel vehicleMakerLabel;
    private JTextField vehicleMakerTextField;
    private JLabel vehicleYearTextLabel;
    private JTextField vehicleYearTextField;
    private JButton completeButton;

    AddVehicle() {
        completeButton.addActionListener(new AddVehiclePage());
    }


    class AddVehiclePage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                GUIMain ref = GUIMain.getInstance();
                ref.setContentPane(new VehicleApplication().mainFrame);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
