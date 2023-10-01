package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class StartPage extends JFrame{
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private JLabel question1;
    private JLabel question2;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton buttonData;
    private JButton buttonNewData;
    private JButton buttonJob;
    private JButton buttonVehicle;
    private JButton goNext;
    private JPanel panel;
    private JTextField username;
    private JPasswordField password;
    FileWriter writerUser = new FileWriter("UserDataBase.txt", true);
    PrintWriter printUser = new PrintWriter(writerUser);

    //This is the constructor as well as the starting point to the ojects inside the main JFrame
    public StartPage() throws IOException {
        question1 = new JLabel("Are you a new user?");
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");

        panel = new JPanel();
        panel.add(question1);
        panel.add(buttonYes);
        panel.add(buttonNo);
        add(panel);

        //buttonYes.setLocation(100,100); needs fixing of positioning

        ActionListener yesListener = new AddNewUserListener();
        buttonYes.addActionListener(yesListener);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            panel.remove(question1);
            panel.remove(buttonYes);
            panel.remove(buttonNo);
            panel.revalidate();
            panel.repaint();

            question1 = new JLabel("Create a new username (_ is not allowed): ");
            username = new JTextField(50);
            String usernameString = username.getText();
            question2 = new JLabel("Create a new password: ");
            password = new JPasswordField(50);
            String passwordString = password.getText();
            goNext = new JButton("Continue");

            panel.add(question1);
            panel.add(username);
            panel.add(question2);
            panel.add(password);
            panel.add(goNext);
            add(panel);

            ActionListener newUser = new submitNewUserListener();
            goNext.addActionListener(newUser);
        }
    }

    //This is the action for the user to continue to the next part by asking for job or vehicle as well as inputting the log in info into the database
    class submitNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                writerUser.write(username.getText());
                printUser.print("_");
                writerUser.write(password.getText());
                printUser.println();

                writerUser.close();
                printUser.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e+"");
            }

            panel.remove(question1);
            panel.remove(username);
            panel.remove(question2);
            panel.remove(password);
            panel.remove(goNext);

            question1 = new JLabel("Do you want to look at your existing jobs and vehicles or would you like to add a new job/vehicle?");
            buttonData = new JButton("Your previous information");
            buttonNewData = new JButton("Submit a new job/vehicle");
            
            panel.add(question1);
            panel.add(buttonData);
            panel.add(buttonNewData);
            
            ActionListener newData = new newDataListener();
            buttonNewData.addActionListener(newData);
        }
    }
    
   class newDataListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent event) {
           panel.remove(question1);
           panel.remove(buttonData);
           panel.remove(buttonNewData);
           
           question1 = 
       }
   }
}
