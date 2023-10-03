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

        loginButton.addActionListener(new ReturningUserPage());


    }

    // on submission will run login logic
    class ReturningUserPage implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                GUIMain ref = GUIMain.getInstance();
                ref.setContentPane(new StartPage().mainFrame);

            } catch (IOException t) {
                throw new RuntimeException(t);
            }

        }
    }
}
