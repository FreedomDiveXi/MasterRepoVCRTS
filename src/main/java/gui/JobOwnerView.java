package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobOwnerView {
    private JPanel mainFrame;
    private JPanel Header;
    private JPanel Body;
    private JPanel footer;
    private JButton history;
    private JButton activeJobs;
    private JButton newJob;
    private JLabel headerText;
    private JButton exportDataButton;

    public JobOwnerView() {
        // Attach action listeners
        activeJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for activeJobs button
                JOptionPane.showMessageDialog(null, "Active Jobs clicked!");
            }
        });
        
        newJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for newJob button
                JOptionPane.showMessageDialog(null, "Create a new job clicked!");
            }
        });
        
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for history button
                JOptionPane.showMessageDialog(null, "History clicked!");
            }
        });
        
        exportDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for exportDataButton
                JOptionPane.showMessageDialog(null, "Export data clicked!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JobOwnerView");
        frame.setContentPane(new JobOwnerView().mainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
