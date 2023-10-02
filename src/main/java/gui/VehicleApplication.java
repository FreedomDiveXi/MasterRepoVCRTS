package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VehicleApplication extends JFrame {
    JPanel mainFrame;
    private JPanel header;
    private JLabel headerTextTop;
    private JLabel headerTextMiddle;
    private JLabel headerTextBottom;
    private JPanel body;
    private JButton newVechicleButton;
    private JPanel innerCenterBody;
    private JButton historyButton;
    private JPanel footer;
    private JButton exportDataButton;


    public VehicleApplication() {
        newVechicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUIMain ref = GUIMain.getInstance();
                    ref.setContentPane(new AddVehicle().mainFrame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
