package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {
    private JPanel MainFrame;
    private JPanel CenterMainFrame;
    private JTextField UsernameField;
    private JPasswordField PasswordField; // Changed to JPasswordField for security.
    private JCheckBox CheckboxValue;
    private JLabel CheckboxLabel;
    private JPanel QuestionBox;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JButton CompletionButton;
    private JPanel Confirmation;

    public Registration() {
        CompletionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = UsernameField.getText();
        String password = new String(PasswordField.getPassword()); // Get password from JPasswordField.

        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(MainFrame, "Username or Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isJobOwner = CheckboxValue.isSelected();

      
        // to actually register the user. For simplicity, we'll just display a message.
        if (isJobOwner) {
            JOptionPane.showMessageDialog(MainFrame, "Registered as Job Owner!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(MainFrame, "Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        // Clear fields after successful registration.
        UsernameField.setText("");
        PasswordField.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration");
        frame.setContentPane(new Registration().MainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
