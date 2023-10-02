package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView {
    private JPanel Mainframe;
    private JPanel Header;
    private JPanel Body;
    private JPanel Footer;
    private JButton dataRequestButton;
    private JButton graphView;
    private JButton exportDataButton;
    private JLabel HeaderText;

    public AdminView() {
        // Initialize the components
        Mainframe = new JPanel();
        Header = new JPanel();
        Body = new JPanel();
        Footer = new JPanel();
        dataRequestButton = new JButton("Data requests");
        graphView = new JButton("Graph View");
        exportDataButton = new JButton("Export data");
        HeaderText = new JLabel("Welcome admin, X amount of jobs have been completed today!");

        // Add listeners to buttons
        dataRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for this button
                JOptionPane.showMessageDialog(null, "Data requests clicked!");
            }
        });

        graphView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for this button
                JOptionPane.showMessageDialog(null, "Graph View clicked!");
            }
        });

        exportDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for this button
                JOptionPane.showMessageDialog(null, "Export data clicked!");
            }
        });

        // Add components to panels
        Header.add(HeaderText);
        Body.add(dataRequestButton);
        Body.add(graphView);
        Footer.add(exportDataButton);

        // Add panels to Mainframe
        Mainframe.add(Header);
        Mainframe.add(Body);
        Mainframe.add(Footer);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AdminView");
        AdminView adminView = new AdminView();
        frame.setContentPane(adminView.Mainframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
