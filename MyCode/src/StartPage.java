import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.time.*;

public class StartPage extends JFrame{
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private LocalDateTime dateTimeNow;
    private JLabel introduction;
    private JLabel question1;
    private JLabel question2;
    private JLabel question3;
    private JLabel question4;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton buttonData;
    private JButton buttonNewData;
    private JButton buttonJob;
    private JButton buttonVehicle;
    private JButton submitJob;
    private JButton submitVehicle;
    private JButton goNext;
    private JButton goBack;
    private JPanel panel;
    private JTextField username;
    private JTextField jobInformation1;
    private JTextField jobInformation2;
    private JTextField jobInformation3;
    private JTextField vehicleInformation1;
    private JTextField vehicleInformation2;
    private JTextField vehicleInformation3;
    private JTextField vehicleInformation4;
    private JPasswordField password;
    FileWriter writerUser = new FileWriter("UserDataBase.txt", true);
    PrintWriter printUser = new PrintWriter(writerUser);
    FileWriter writerVehicle = new FileWriter("VehicleDataBase.txt", true);
    PrintWriter printVehicle = new PrintWriter(writerVehicle);
    FileWriter writerJob = new FileWriter("JobDataBase.txt", true);
    PrintWriter printJob = new PrintWriter(writerJob);

    //This is the constructor as well as the starting point to the objects inside the main JFrame
    public StartPage() throws IOException {
    	setSize(FRAME_WIDTH, FRAME_HEIGHT);
    	
    	introduction = new JLabel("This application allows users to ");
        question1 = new JLabel("Are you a new user?");
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");

        panel = new JPanel();
        panel.add(introduction);
        panel.add(question1);
        panel.add(buttonYes);
        panel.add(buttonNo);
        add(panel);

        ActionListener yesListener = new AddNewUserListener();
        buttonYes.addActionListener(yesListener);
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            question1 = new JLabel("Create a new username (: is not allowed): ");
            username = new JTextField(50);
            question2 = new JLabel("Create a new password: ");
            password = new JPasswordField(50);
            goNext = new JButton("Continue");

            panel.add(question1);
            panel.add(username);
            panel.add(question2);
            panel.add(password);
            panel.add(goNext);
            add(panel);

            ActionListener nextPage = new nextPageListener();
            goNext.addActionListener(nextPage);
        }
    }
    
    //Continue button which add the user log in and asks if they want to see existing info or add new info 
    class nextPageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	try {
                writerUser.write(username.getText());
                printUser.print(" : ");
                writerUser.write(password.getText());
                printUser.println();

                writerUser.close();
                printUser.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e+"");
            }
        	
        	panel.removeAll();
            panel.revalidate();
            panel.repaint();

            question1 = new JLabel("Do you want to look at your existing jobs and vehicles or would you like to add a new job/vehicle?");
            buttonData = new JButton("Your previous information");
            buttonNewData = new JButton("Submit a new job/vehicle");
            goBack = new JButton("Return to previous page");

            panel.add(question1);
            panel.add(buttonData);
            panel.add(buttonNewData);
            panel.add(goBack);

            ActionListener newData = new newDataListener();
            buttonNewData.addActionListener(newData);
            ActionListener previousPage = new AddNewUserListener();
            goBack.addActionListener(previousPage);
        }
    }

    //This is the action of asking for a new job or a new vehicle
   class newDataListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent event) {
           panel.removeAll();
           panel.revalidate();
           panel.repaint();

           question1 = new JLabel("Do you want to submit a new job or a new vehicle?");
           buttonJob = new JButton("Job");
           buttonVehicle = new JButton("Vehicle");
           goBack = new JButton("Return to previous page");

           panel.add(question1);
           panel.add(buttonJob);
           panel.add(buttonVehicle);
           panel.add(goBack);

           ActionListener newJob = new newJobListener();
           buttonJob.addActionListener(newJob);
           ActionListener newVehicle = new newVehicleListener();
           buttonVehicle.addActionListener(newVehicle);
           ActionListener previousPage = new nextPageListener();
           goBack.addActionListener(previousPage);
       }
   }

   //This is asking for new job information
    class newJobListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
        	panel.removeAll();
            panel.revalidate();
            panel.repaint();
            
            question1 = new JLabel("Client ID");
            jobInformation1 = new JTextField(50);
            question2 = new JLabel("Approximatly how long will this job/task take, please just type in this format days:hours:mins?");
            jobInformation2 = new JTextField(50);
            question3 = new JLabel("If needed what is this job/task deadline, please type in this formate month:day:year");
            jobInformation3 = new JTextField(50);
            submitJob = new JButton("Submit");
            goBack = new JButton("Return to previous page");
            
            panel.add(question1);
            panel.add(jobInformation1);
            panel.add(question2);
            panel.add(jobInformation2);
            panel.add(question3);
            panel.add(jobInformation3);
            panel.add(submitJob);
            panel.add(goBack);
            
            ActionListener submit = new submitJobListener();
            submitJob.addActionListener(submit);
            ActionListener previousPage = new newDataListener();
            goBack.addActionListener(previousPage);
        }
    }
    
    //This submit the job into the txt file
    class submitJobListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event){
    		dateTimeNow = LocalDateTime.now();
    		try {
    			writerJob.write(dateTimeNow.toString());
    			printJob.print(" : ");
                writerJob.write(jobInformation1.getText());
                printJob.print(" : ");
                writerJob.write(jobInformation2.getText());
                printJob.print(" : ");
                writerJob.write(jobInformation3.getText());
                printJob.println();

                writerJob.close();
                printJob.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e+"");
            }
    		
    		jobInformation1.setText("");
    		jobInformation2.setText("");
    		jobInformation3.setText("");
    	}
    }

    //This is asking for new vehicle information
    class newVehicleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
        	panel.removeAll();
            panel.revalidate();
            panel.repaint();
            
            question1 = new JLabel("Owner ID");
            vehicleInformation1 = new JTextField(50);
            question2 = new JLabel("What is the vehicle's model?");
            vehicleInformation2 = new JTextField(50);
            question3 = new JLabel("What is the vehicle's make/brand?");
            vehicleInformation3 = new JTextField(50);
            question4 = new JLabel("What is the vehicle's year");
            vehicleInformation4 = new JTextField(50);
            submitVehicle = new JButton("Submit");
            goBack = new JButton("Return to previous page");
            
            panel.add(question1);
            panel.add(vehicleInformation1);
            panel.add(question2);
            panel.add(vehicleInformation2);
            panel.add(question3);
            panel.add(vehicleInformation3);
            panel.add(question4);
            panel.add(vehicleInformation4);
            panel.add(submitVehicle);
            panel.add(goBack);
            
            ActionListener submit = new submitVehicleListener();
            submitVehicle.addActionListener(submit);
            ActionListener previousPage = new newDataListener();
            goBack.addActionListener(previousPage);
        }
    }
    
    //This submits the vehicle into the txt file
    class submitVehicleListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event){
    		dateTimeNow = LocalDateTime.now();
    		try {
    			writerVehicle.write(dateTimeNow.toString());
    			printVehicle.print(" : ");
                writerVehicle.write(vehicleInformation1.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleInformation2.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleInformation3.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleInformation4.getText());
                printVehicle.println();

                writerVehicle.close();
                printVehicle.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e+"");
            }
    		
    		vehicleInformation1.setText("");
    		vehicleInformation2.setText("");
    		vehicleInformation3.setText("");
    		vehicleInformation4.setText("");
    	}
    }
}