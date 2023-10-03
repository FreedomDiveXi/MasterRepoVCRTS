package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddVehicle extends JFrame {
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JTextField vehicleModelTextField;
    private JTextField vehicleMakerTextField;
    private JLabel vehicleModelLabel;
    private JTextField vehicleYearTextField;
    private JButton completeButton;
    private JLabel vehicleMakerLabel;
    private JLabel vehicleYearTextLabel;

    AddVehicle() {
        completeButton.addActionListener(new VehicleUserApplicationPage());
    }

    class VehicleUserApplicationPage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // will only continue onto the vehicle application if all the
            // required text fields are filled out
            if (!vehicleModelTextField.getText().isEmpty() &&
                    !vehicleMakerTextField.getText().isEmpty() &&
                    !vehicleYearTextField.getText().isEmpty()
            ) {
                try {
                    GUIMain ref = GUIMain.getInstance();

                    ref.registerNewVehicle(vehicleModelTextField.getText(),
                            vehicleMakerTextField.getText(),
                            vehicleYearTextField.getText());
                    ref.setContentPane(new VehicleApplication().mainFrame);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }
}
