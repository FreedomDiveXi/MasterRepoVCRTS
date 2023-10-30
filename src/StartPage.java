import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
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
    private JLabel question5;
    private JButton jobOwnerButton;
    private JButton vehicleOwnerButton;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton buttonData;
    private JButton buttonJob;
    private JButton buttonVehicle;
    private JButton submitJob;
    private JButton submitVehicle;
    private JButton goNext;
    private JButton goBack;
    private JButton buttonController;
    private JPanel panel;
    private JTextField username;
    private JTextField clientID;
    private JTextField jobID;
    private JTextField jobDuration;
    private JTextField jobDeadline;
    private JTextField ownerID;
    private JTextField vehicleID;
    private JTextField vehicleModel;
    private JTextField vehicleMake;
    private JTextField vehicleYear;
    private JPasswordField password;
    CloudController run = new CloudController();
    private ButtonGroup group;

    //This is the constructor as well as the starting point to the objects inside the main JFrame
    public StartPage() throws IOException {
    	setSize(FRAME_WIDTH, FRAME_HEIGHT);
    	introduction = new JLabel("<html>" + "This application allows users to complete certain tasks that would require " + "<br/>" + "an immense amount of power that you simply do not have or input your own " + "<br/>" + "unoccupied car, so we can utilize the computational power that a car has." + "</html>");
        buttonController = new JButton("Continue");
        
        panel = new JPanel();
        panel.add(introduction);
        panel.add(buttonController);
        add(panel);
        
        ActionListener homePage = new homePageListener();
        buttonController.addActionListener(homePage);
    }
    
    //This is the home page listener
    class homePageListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		panel.removeAll();
    		panel.revalidate();
    		panel.repaint();
    		
    		question1 = new JLabel("Are you a new user?");
            buttonYes = new JButton("Yes");
            buttonNo = new JButton("No");
            question2 = new JLabel("Are you the admin/VC Controller?");
            buttonController = new JButton("Controller");
            
            panel.add(question1);
            panel.add(buttonYes);
            panel.add(buttonNo);
            panel.add(question2);
            panel.add(buttonController);
            
            ActionListener yesListener = new AddNewUserListener();
            buttonYes.addActionListener(yesListener);
            ActionListener controllerListener = new ControllerListener();
            buttonController.addActionListener(controllerListener);
    	}
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            panel.removeAll();
            panel.revalidate();
            panel.repaint();

            question1 = new JLabel("Create a new username (':' is not allowed): ");
            username = new JTextField(50);
            question2 = new JLabel("Create a new password: ");
            password = new JPasswordField(50);
            question3 = new JLabel("Are you a job owner or vehicle owner? Choose only one.");
            jobOwnerButton = new JButton("Job Owner");
            vehicleOwnerButton = new JButton("Vehicle Owner");
            question4 = new JLabel("You have not selected Job or Vehicle Owner yet.");
        	goNext = new JButton("Continue");

            panel.add(question1);
            panel.add(username);
            panel.add(question2);
            panel.add(password);
            panel.add(question3);
            panel.add(jobOwnerButton);
            panel.add(vehicleOwnerButton);
            panel.add(question4);
            panel.add(goNext);
            
            ActionListener userJob = new userIsJobOwnerListener();
            jobOwnerButton.addActionListener(userJob);
            ActionListener userVehicle = new userIsVehicleOwnerListener();
            vehicleOwnerButton.addActionListener(userVehicle);
        }
    }
    
    //The controller action caller 
    class ControllerListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		panel.removeAll();
    		panel.revalidate();
    		panel.repaint();
    		
    		buttonData = new JButton("Calculate completion time");
            goBack = new JButton("Return to home page");
    		
    		panel.add(buttonData);
            panel.add(goBack);
    		
    		ActionListener calculate = new calculateTimeListener();
    		buttonData.addActionListener(calculate);
            ActionListener homePage = new homePageListener();
            goBack.addActionListener(homePage);
    	}
    }
    
    //This is the button to make the user a job owner
    class userIsJobOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	panel.remove(question4);
        	panel.remove(goNext);
        	panel.revalidate();
    		panel.repaint();
    		
        	question4 = new JLabel("You have selected job owner.");
        	goNext = new JButton("Continue");
        	
        	panel.add(question4);
            panel.add(goNext);
        	
            ActionListener nextPageListener = new nextPageListenerJob();
            goNext.addActionListener(nextPageListener);
        }
    }
    
    //This is the button to make the user a vehicle owner
    class userIsVehicleOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	panel.remove(question4);
        	panel.remove(goNext);
        	panel.revalidate();
    		panel.repaint();
    		
        	question4 = new JLabel("You have selected vehicle owner.");
        	goNext = new JButton("Continue");
        	
        	panel.add(question4);
        	panel.add(goNext);
        	
        	ActionListener nextPageListener = new nextPageListenerVehicle();
            goNext.addActionListener(nextPageListener);
        }
    }
    
    //This is the next page listener for job
    class nextPageListenerJob implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	run.createJobOwner(username.getText(), password.getText());
        	
        	try {
        	    FileWriter writerUser = new FileWriter("UserDataBase.txt", true);
        	    PrintWriter printUser = new PrintWriter(writerUser);
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
            
            question1 = new JLabel("Do you want to submit a job or see your previous information");
            buttonData = new JButton("See your previous information");
            buttonJob = new JButton("Submit a job");
            goBack = new JButton("Return to home page");
            	
            panel.add(question1);
            panel.add(buttonData);
            panel.add(buttonJob);
            panel.add(goBack);

            ActionListener newJob = new newJobListener();
            buttonJob.addActionListener(newJob);
            ActionListener homePage = new homePageListener();
            goBack.addActionListener(homePage);
        }
    }
    
    //This is the next page listener for vehicle
    class nextPageListenerVehicle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	run.createJobOwner(username.getText(), password.getText());
        	
        	try {
        	    FileWriter writerUser = new FileWriter("UserDataBase.txt", true);
        	    PrintWriter printUser = new PrintWriter(writerUser);
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

            question1 = new JLabel("Do you want to submit a vehicle or see your previous information");
            buttonData = new JButton("See your previous information");
            buttonVehicle = new JButton("Submit a vehicle");
            goBack = new JButton("Return to home page");
            	
            panel.add(question1);
            panel.add(buttonData);
            panel.add(buttonVehicle);
            panel.add(goBack);
            
            ActionListener newVehicle = new newVehicleListener();
            buttonVehicle.addActionListener(newVehicle);
            ActionListener homePage = new homePageListener();
            goBack.addActionListener(homePage);
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
            clientID = new JTextField(50);
            question2 = new JLabel("Job ID");
            jobID = new JTextField(50);
            question3 = new JLabel("Approximatly how long will this job take, please just type in the total amount of hours?");
            jobDuration = new JTextField(50);
            question4 = new JLabel("If needed what is this job's deadline, please type in this formate month-day-year");
            jobDeadline = new JTextField(50);
            submitJob = new JButton("Submit");
            goBack = new JButton("Return to home page");
            
            panel.add(question1);
            panel.add(clientID);
            panel.add(question2);
            panel.add(jobID);
            panel.add(question3);
            panel.add(jobDuration);
            panel.add(question4);
            panel.add(jobDeadline);
            panel.add(submitJob);
            panel.add(goBack);
            
            ActionListener submit = new submitJobListener();
            submitJob.addActionListener(submit);
            ActionListener homePage = new homePageListener();
            goBack.addActionListener(homePage);
        }
    }
    
    //This submit the job into the txt file
    class submitJobListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event){
    		dateTimeNow = LocalDateTime.now();
    		try {
    		    FileWriter writerJob = new FileWriter("JobDataBase.txt", true);
    		    PrintWriter printJob = new PrintWriter(writerJob);
    			writerJob.write(dateTimeNow.toString());
    			printJob.print(" : ");
                writerJob.write(clientID.getText());
                printJob.print(" : ");
                writerJob.write(jobID.getText());
                printJob.print(" : ");
                writerJob.write(jobDuration.getText());
                printJob.print(" : ");
                writerJob.write(jobDeadline.getText());
                printJob.println();
                
                writerJob.close();
                printJob.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e+"");
            }
    		
    		if (jobDeadline.getText().equals("")) {
    			run.createJob(clientID.getText(), jobID.getText(), jobDuration.getText());
    		}
    		else {
    			run.createJob(clientID.getText(), jobID.getText(), jobDuration.getText(), jobDeadline.getText());
    		}
    		
    		clientID.setText("");
    		jobID.setText("");
    		jobDuration.setText("");
    		jobDeadline.setText("");
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
            ownerID = new JTextField(50);
            question2 = new JLabel("Vehicle ID");
            vehicleID = new JTextField(50);
            question3 = new JLabel("What is the vehicle's model?");
            vehicleModel = new JTextField(50);
            question4 = new JLabel("What is the vehicle's make/brand?");
            vehicleMake = new JTextField(50);
            question5 = new JLabel("What is the vehicle's year");
            vehicleYear = new JTextField(50);
            submitVehicle = new JButton("Submit");
            goBack = new JButton("Return to home page");
            
            panel.add(question1);
            panel.add(ownerID);
            panel.add(question2);
            panel.add(vehicleID);
            panel.add(question3);
            panel.add(vehicleModel);
            panel.add(question4);
            panel.add(vehicleMake);
            panel.add(question5);
            panel.add(vehicleYear);
            panel.add(submitVehicle);
            panel.add(goBack);
            
            ActionListener submit = new submitVehicleListener();
            submitVehicle.addActionListener(submit);
            ActionListener homePage = new homePageListener();
            goBack.addActionListener(homePage);
        }
    }
    
    //This submits the vehicle into the txt file
    class submitVehicleListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event){
    		dateTimeNow = LocalDateTime.now();
    		try {
    			FileWriter writerVehicle = new FileWriter("VehicleDataBase.txt", true);
    		    PrintWriter printVehicle = new PrintWriter(writerVehicle);
    			writerVehicle.write(dateTimeNow.toString());
    			printVehicle.print(" : ");
                writerVehicle.write(ownerID.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleID.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleModel.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleMake.getText());
                printVehicle.print(" : ");
                writerVehicle.write(vehicleYear.getText());
                printVehicle.println();
                
                writerVehicle.close();
                printVehicle.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e+"");
            }
    		
    		run.createVehicle(ownerID.getText(), vehicleID.getText(), vehicleModel.getText(), vehicleMake.getText(), vehicleYear.getText());
    		
    		ownerID.setText("");
    		vehicleID.setText("");
    		vehicleModel.setText("");
    		vehicleMake.setText("");
    		vehicleYear.setText("");
    	}
    }
    
    //This is the action  listener for calculation complete time within controller
    class calculateTimeListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		JFrame jobCompletionController = new JFrame();

    		JPanel panel2 = new JPanel();

            String str = "<html>";
            str += run.startProcessing();
            str += "</html>";

            panel2.add(new JLabel(str));
    		jobCompletionController.add(panel2);
    		
    		jobCompletionController.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    		jobCompletionController.setTitle("Controller list of current completed jobs' ID and completion time");
    		jobCompletionController.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		jobCompletionController.setVisible(true);
    		
    	}
    }
}
