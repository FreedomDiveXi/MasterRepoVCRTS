package gui;

import users.vehicleOwner.VehicleOwner;

import javax.swing.*;
import java.awt.event.*;

public class StartPage extends JFrame{
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private JLabel question1;
    private JLabel question2;
    private JButton buttonYes;
    private JButton buttonNo;
    private JPanel panel;
    private JTextField username;
    private JTextField password;

    public StartPage() {
        question1 = new JLabel("Are you a new user?");
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");

        //buttonYes.setLocation(100,100); needs fixing of positioning

        ActionListener yesListener = new AddNewUserListener();
        ActionListener noListener = new ReturningUser();

        buttonYes.addActionListener(yesListener);
        buttonNo.addActionListener(noListener);

        panel = new JPanel();
        panel.add(question1);
        panel.add(buttonYes);
        panel.add(buttonNo);
        add(panel);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);


    }

    // assumes they have no account yet.
    class AddNewUserListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            dispose();

            Registration registration = new Registration();
            JFrame registrationFrame = new JFrame("Registration");
            registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registrationFrame.setContentPane(registration.mainFrame);
            registrationFrame.pack();
            registrationFrame.setVisible(true);
        }
    }

    class ReturningUser implements ActionListener {
        public void actionPerformed(ActionEvent event){
            dispose();

            VehicleOwnerView vehiclePage = new VehicleOwnerView();
            JFrame vehicleFrame = new JFrame("VehicleView");
            vehicleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vehicleFrame.setContentPane(vehiclePage.mainFrame);
            vehicleFrame.pack();
            vehicleFrame.setVisible(true);
        }
    }
}
