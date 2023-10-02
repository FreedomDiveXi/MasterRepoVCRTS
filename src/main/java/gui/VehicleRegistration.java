package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VehicleRegistration extends JFrame {
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JTextField vehicleTextField;
    private JTextField vehicleMakerTextField;
    private JLabel vehicleModelLabel;
    private JTextField vehicleYearTextField;
    private JButton completeButton;
    private JLabel vehicleMakerLabel;
    private JLabel vehicleYearTextLabel;

    VehicleRegistration(){

        completeButton.addActionListener(new VehicaleUserApplicationPage());

    }
    class VehicaleUserApplicationPage implements ActionListener {
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
