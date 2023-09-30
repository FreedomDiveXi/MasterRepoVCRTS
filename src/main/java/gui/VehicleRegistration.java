package gui;

import javax.swing.*;

public class VehicleRegistration {
    private JPanel MainFrame;
    private JPanel CenterMainFrame;
    private JTextField ModelTextField;
    private JTextField MakerTextField;
    private JTextField YearTextField;
    private JPanel Confirmation;
    private JButton CompletionButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("VehicleRegistration");
        frame.setContentPane(new VehicleRegistration().MainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
