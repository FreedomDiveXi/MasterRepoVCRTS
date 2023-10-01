package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class NewUserSignUp extends JFrame {
    FileWriter writeUser = new FileWriter("UserDataBase.txt", true);
    PrintWriter printUser = new PrintWriter(writeUser);

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

    NewUserSignUp() throws IOException {
        nextPageButton.addActionListener(new GoToVehicleRegistration());
    }

    // on submission this logic will trigger
    class GoToVehicleRegistration implements ActionListener {

        /**
         * todo: implement feature
         * this assumes that when the user submits they're vehicle people.
         * though have to add a thing in which when the checkbox is clicked
         * isntead of calling register vehicle and also showing the rest of the
         * form we simply guide the user to the correct portal and call the
         * right controller to add them to the right list.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // assumes that we're only making vehicle people
                GUIMain ref = GUIMain.getInstance();
//                ref.registerVehicleUser(usernameTextField.getText(), Arrays.toString(passwordTextField.getPassword()));
                writeUser.write(usernameTextField.getText());
                printUser.print("_fk");
                writeUser.write(Arrays.toString(passwordTextField.getPassword()));
                printUser.println();
                writeUser.close();
                printUser.close();
                ref.setContentPane(new VehicleRegistration().mainFrame);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
