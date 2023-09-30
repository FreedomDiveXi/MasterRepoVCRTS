package gui;

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
        buttonYes.addActionListener(yesListener);

        panel = new JPanel();
        panel.add(question1);
        panel.add(buttonYes);
        panel.add(buttonNo);
        add(panel);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    class AddNewUserListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            panel.remove(question1);
            panel.remove(buttonYes);
            panel.remove(buttonNo);
            panel.revalidate();
            panel.repaint();

            question1 = new JLabel("Create a new username: ");
            username = new JTextField(50);
            question2 = new JLabel("Create a new password: ");
            password = new JTextField(50);

            panel.add(question1);
            panel.add(username);
            panel.add(question2);
            panel.add(password);
            add(panel);
        }
    }
}
