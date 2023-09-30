package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static utils.GuiHelper.showGui;

public class StartPage extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    FileWriter writerUser = new FileWriter("UserDataBase.txt", true);
    PrintWriter printUser = new PrintWriter(writerUser);
    private JLabel question1;
    private JLabel question2;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton goNext;
    private JPanel panel;
    private JTextField username;
    private JPasswordField password;

    public StartPage() throws IOException {
        question1 = new JLabel("Are you a new user?");
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");


        panel = new JPanel();
        panel.add(question1);
        panel.add(buttonYes);
        panel.add(buttonNo);
        add(panel);

        //buttonYes.setLocation(100,100); needs fixing of positioning

        ActionListener yesListener = new AddNewUserListener();
        ActionListener noLister = new ReturningUser();
        buttonNo.addActionListener(noLister);
        buttonYes.addActionListener(yesListener);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    // assumes they have no account yet.
    class AddNewUserListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            panel.remove(question1);
            panel.remove(buttonYes);
            panel.remove(buttonNo);
            panel.revalidate();
            panel.repaint();

            question1 = new JLabel("Create a new username (_ is not allowed): ");
            username = new JTextField(50);
            String usernameString = username.getText();
            question2 = new JLabel("Create a new password: ");
            password = new JPasswordField(50);
            String passwordString = password.getText();
            goNext = new JButton("Continue");

            panel.add(question1);
            panel.add(username);
            panel.add(question2);
            panel.add(password);
            panel.add(goNext);
            add(panel);

            ActionListener newUser = new submitNewUserListener();
            goNext.addActionListener(newUser);

        }
    }

    class submitNewUserListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            try {
                writerUser.write(username.getText());
                printUser.print("_");
                writerUser.write(password.getText());
                printUser.println();

                writerUser.close();
                printUser.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e + "");
            }
            dispose();

            Registration registration = new Registration();
            JFrame registrationFrame = new JFrame("Registration");
            showGui(registrationFrame, registration.mainFrame);
        }
    }

    class ReturningUser implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            dispose();

            VehicleOwnerView vehiclePage = new VehicleOwnerView();
            JFrame vehicleFrame = new JFrame("VehicleView");
            showGui(vehicleFrame, vehiclePage.mainFrame);

        }
    }
}
