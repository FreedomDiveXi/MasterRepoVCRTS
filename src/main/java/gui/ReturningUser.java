package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReturningUser extends JFrame {
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JLabel headerText;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;

    ReturningUser() {
        loginButton.addActionListener(new GoToUserPage());
    }

    // on submission this logic will trigger
    class GoToUserPage implements ActionListener {
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
