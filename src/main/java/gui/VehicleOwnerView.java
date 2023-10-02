package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleOwnerView {
    private JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JButton newVehicle; // Corrected the spelling of 'newVechicle' to 'newVehicle'
    private JPanel innerCenterBody;
    private JButton activeJobs;
    private JButton history;
    private JButton findAJobButton;
    private JButton exportData;
    private JLabel headerText;
    private JLabel headerText2;
    private JLabel headerText3;

    public VehicleOwnerView() {
        // Initialize ActionListeners for the buttons.
        newVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the 'newVehicle' button.
                JOptionPane.showMessageDialog(mainFrame, "New Vehicle Button Clicked!");
            }
        });

        activeJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the 'activeJobs' button.
                JOptionPane.showMessageDialog(mainFrame, "Active Jobs Button Clicked!");
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the 'history' button.
                JOptionPane.showMessageDialog(mainFrame, "History Button Clicked!");
            }
        });

        findAJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the 'findAJobButton' button.
                JOptionPane.showMessageDialog(mainFrame, "Find a Job Button Clicked!");
            }
        });

        exportData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the 'exportData' button.
                JOptionPane.showMessageDialog(mainFrame, "Export Data Button Clicked!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VehicleOwnerView");
        frame.setContentPane(new VehicleOwnerView().mainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
