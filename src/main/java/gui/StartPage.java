package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class StartPage extends JFrame {
    JPanel mainFrame;
    private JPanel body;
    private JButton returningUserButton;
    private JButton signUpButton;
    private JLabel headerText;
    private JPanel header;

    StartPage() {

        returningUserButton.addActionListener(new ReturningUserPage());
        signUpButton.addActionListener(new NewUserPage());

    }

    // on the click of the returning user this will trigger
    class ReturningUserPage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                GUIMain ref = GUIMain.getInstance();
                ref.setContentPane(new ReturningUser().mainFrame);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    // on click of new user this will trigger
    class NewUserPage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                GUIMain ref = GUIMain.getInstance();
                ref.setContentPane(new ReturningUser().mainFrame);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
