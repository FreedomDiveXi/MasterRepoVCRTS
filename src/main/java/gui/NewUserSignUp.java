package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class NewUserSignUp extends JFrame {
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JButton nextPageButton;
    private JPanel top;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JPanel middle;
    private JLabel checkboxLabel;
    private JCheckBox checkboxValue;
    private JPanel bottom;

    NewUserSignUp(){
        nextPageButton.addActionListener(new VehicleRegistrationPage());
    }

    // on submission this logic will trigger
    class VehicleRegistrationPage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                GUIMain ref = GUIMain.getInstance();
                if(checkboxValue.isSelected()){
                    ref.setContentPane(new JobOwnerApplication().mainFrame);
                }else{
                    ref.setContentPane(new VehicleRegistration().mainFrame);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
