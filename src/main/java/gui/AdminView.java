package gui;

import javax.swing.*;

public class AdminView {
    private JPanel Mainframe;
    private JPanel Header;
    private JPanel Body;
    private JPanel Footer;
    private JButton dataRequestButton;
    private JButton graphView;
    private JButton exportDataButton;
    private JLabel HeaderText;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AdminView");
        frame.setContentPane(new AdminView().Mainframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
