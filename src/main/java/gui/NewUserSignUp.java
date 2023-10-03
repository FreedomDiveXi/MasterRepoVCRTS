package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    NewUserSignUp() {
        nextPageButton.addActionListener(new VehicleRegistrationPage());
    }

    // on submission this logic will trigger
    class VehicleRegistrationPage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // converts char [] into a string
            String string_op = Stream
                    .of(passwordTextField.getPassword())
                    .map(String::new)
                    .collect(Collectors.joining());

            if (!usernameTextField.getText().isEmpty() && !string_op.isEmpty()) {
                try {

                    // if the text fields are not filled, nothing will happen.
                    GUIMain ref = GUIMain.getInstance();
                    // if the job owner is selected will show that view, if not
                    // will continue to the vehicle registration
                    if (checkboxValue.isSelected()) {
                        ref.registerNewJobUser(usernameTextField.getText(),
                                string_op);
                        ref.setContentPane(new JobApplication().mainFrame);
                    } else {
                        ref.registerNewVehicleUser(usernameTextField.getText(), string_op);
                        ref.setContentPane(new AddVehicle().mainFrame);
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
