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


    VehicleRegistration() {
        completeButton.addActionListener(new GoToNextPage());
    }

    // on submission this logic will trigger
    class GoToNextPage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                GUIMain ref = GUIMain.getInstance();
                ref.setContentPane(new VehicleOwnerView().mainFrame);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}
