package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JobApplication extends JFrame {
    JPanel mainFrame;
    private JPanel Header;
    private JPanel Body;
    private JPanel footer;
    private JButton history;
    private JButton activeJobs;
    private JButton newJob;
    private JLabel headerText;
    private JButton exportDataButton;
    private JButton signOutButton;

    public JobApplication() {
        newJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUIMain ref = GUIMain.getInstance();
                    ref.setContentPane(new AddJob().mainFrame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUIMain ref = GUIMain.getInstance();
                    ref.setContentPane(new StartPage().mainFrame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
