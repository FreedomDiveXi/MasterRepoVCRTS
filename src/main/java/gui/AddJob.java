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
    private JTextField jobDurationTextField;
    private JLabel jobDurationLabel;
    private JLabel jobDeadlineLabel;
    private JButton submitJobButton;
    private JTextField jobDeadlineTextField;

    public AddJob() {
        submitJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (
                        !jobDeadlineTextField.getText().isEmpty() && !jobDurationTextField.getText().isEmpty()
                ) {
                    try {
                        GUIMain ref = GUIMain.getInstance();
                        ref.registerNewJob(jobDeadlineTextField.getText(),
                                jobDurationTextField.getText());
                        ref.setContentPane(new JobApplication().mainFrame);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
