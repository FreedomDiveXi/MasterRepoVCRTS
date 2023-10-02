package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddJob {
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JTextField deadlineTextField;
    private JTextArea jobDescription;
    private JLabel deadlineLabel;
    private JLabel jobDescriptionLabel;
    private JButton submitJobButton;

    public AddJob() {
        submitJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUIMain ref = GUIMain.getInstance();
                    ref.setContentPane(new JobApplication().mainFrame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}
