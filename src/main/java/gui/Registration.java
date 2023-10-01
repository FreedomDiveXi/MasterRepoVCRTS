package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Registration extends JFrame {
    JPanel mainFrame;
    private JPanel CenterMainFrame;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JCheckBox checkboxValue;
    private JLabel checkBoxLabel;
    private JPanel QuestionBox;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton nextPageButton;
    private JPanel Confirmation;

    Registration(){
        nextPageButton.addActionListener(new GoToNextPage());
    }

    class GoToNextPage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                GUIMain ref = GUIMain.getInstance();
                ref.setContentPane(new VehicleRegistration().mainFrame);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
