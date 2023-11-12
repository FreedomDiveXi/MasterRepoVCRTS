import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class StartPage extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
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
    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
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
    private Boolean newUser;
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;

    //This is the constructor as well as the starting point to the objects inside the main JFrame
    public StartPage() throws IOException {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        introduction = new JLabel("<html>" + "This application allows users to complete certain tasks that would require " + "<br/>" + "an immense amount of power that you simply do not have or input your own " + "<br/>" + "unoccupied car, so we can utilize the computational power that a car has." + "</html>");
        introduction.setFont(new Font("Arial", Font.BOLD, 14));
        introduction.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel2 = new JPanel();
        panel2.setBorder(new EmptyBorder(50, 0, 0, 0));
        
        goNext = new JButton("Continue");
        goNext.setPreferredSize(new Dimension(100, 40));
        goNext.setFont(new Font("Arial", Font.BOLD, 12));

        panel.add(introduction);
        panel2.add(goNext);
        add(panel);
        add(panel2);

        // server initialization
        System.out.println("client is running");
        try {
            Socket socket = new Socket("localhost", 9806);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        ActionListener homePage = new homePageListener();
        goNext.addActionListener(homePage);

    }

    //This is the home page listener
    class homePageListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		panel.removeAll();
    		panel.revalidate();
    		panel.repaint();
    		panel2.removeAll();
    		panel2.revalidate();
    		panel2.repaint();

    		question1 = new JLabel("Are you a new user?");
    		question1.setFont(new Font("Arial", Font.BOLD, 14));
    		question1.setBorder(new EmptyBorder(0, 0, 0, 0));
    		question1.setAlignmentX(Component.CENTER_ALIGNMENT);
    		
            buttonYes = new JButton("Yes");
            buttonYes.setPreferredSize(new Dimension(100, 40));
            buttonYes.setFont(new Font("Arial", Font.BOLD, 12));
            
            buttonNo = new JButton("No");
            buttonNo.setPreferredSize(new Dimension(100, 40));
            buttonNo.setFont(new Font("Arial", Font.BOLD, 12));

            panel.add(question1);
            panel2.add(buttonYes);
            panel2.add(buttonNo);

            ActionListener yesListener = new AddNewUserListener();
            buttonYes.addActionListener(yesListener);
        }
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JFrame UserLogin = new JFrame();
            UserLogin.setLayout(new BoxLayout(UserLogin.getContentPane(), BoxLayout.Y_AXIS));
            newUser = true;
            
            panel = new JPanel();
            panel.setBorder(new EmptyBorder(20,0,10,0));
            
            question1 = new JLabel("Create a new username (':' is not allowed): ");
            question1.setFont(new Font("Arial", Font.BOLD, 14));
    		question1.setAlignmentX(Component.LEFT_ALIGNMENT);
            username = new JTextField(25);
            
            question2 = new JLabel("Create a new password: ");
            question2.setFont(new Font("Arial", Font.BOLD, 14));
            question2.setBorder(new EmptyBorder(50, 0, 0, 0));
    		question2.setAlignmentX(Component.LEFT_ALIGNMENT);
            password = new JPasswordField(25);
            
            question3 = new JLabel("Are you a job owner or vehicle owner? Choose only one.");
            question3.setFont(new Font("Arial", Font.BOLD, 14));
            question3.setBorder(new EmptyBorder(75, 0, 10, 0));
    		question3.setAlignmentX(Component.LEFT_ALIGNMENT);
    		
    		panel2 = new JPanel();
    		panel2.setBorder(new EmptyBorder(100,0,0,0));
    		
            jobOwnerButton = new JButton("Job Owner");
            jobOwnerButton.setPreferredSize(new Dimension(100, 40));
            jobOwnerButton.setFont(new Font("Arial", Font.BOLD, 12));
            
            vehicleOwnerButton = new JButton("Vehicle Owner");
            vehicleOwnerButton.setPreferredSize(new Dimension(100, 40));
            vehicleOwnerButton.setFont(new Font("Arial", Font.BOLD, 12));
            
            question4 = new JLabel("You have not selected Job or Vehicle Owner yet.");
            question4.setFont(new Font("Arial", Font.BOLD, 14));
            question4.setBorder(new EmptyBorder(125, 0, 10, 0));
    		question4.setAlignmentX(Component.LEFT_ALIGNMENT);
    		
    		panel3 = new JPanel();
    		panel3.setBorder(new EmptyBorder(150, 0, 0, 0));
    		
            goNext = new JButton("Continue");
            goNext.setPreferredSize(new Dimension(100, 40));
            goNext.setFont(new Font("Arial", Font.BOLD, 12));

            panel.add(question1);
            panel.add(username);
            UserLogin.add(question2);
            UserLogin.add(password);
            UserLogin.add(question3);
            panel2.add(jobOwnerButton);
            panel2.add(vehicleOwnerButton);
            panel2.add(question4);
            panel3.add(goNext);
            UserLogin.add(panel);
            UserLogin.add(panel2);
            UserLogin.add(panel3);
            UserLogin.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            UserLogin.setTitle("New User");
            UserLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            UserLogin.setVisible(true);

            ActionListener userJob = new userIsJobOwnerListener();
            jobOwnerButton.addActionListener(userJob);
            ActionListener userVehicle = new userIsVehicleOwnerListener();
            vehicleOwnerButton.addActionListener(userVehicle);
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
            // instead of creating the object here, we funnel the response to the server so that
            // the server can create it.
            String messageOut = "";

            try {
                if (newUser) {
                    System.out.println("User Request");
                    messageOut = "user-request-ju" + "::" + username.getText() + "::" + password.getText();
                    outputStream.writeUTF(messageOut);
                }
                newUser = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(inputStream.readUTF().equals("user-accept")){
                    System.out.println("user has been accepted");
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();

                    question1 = new JLabel("Do you want to submit a job or see your previous information");
                    buttonData = new JButton("See your previous information");
                    buttonJob = new JButton("Submit a job");

                    panel.add(question1);
                    panel.add(buttonData);
                    panel.add(buttonJob);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ActionListener newJob = new newJobListener();
            buttonJob.addActionListener(newJob);
        }
    }

    //This is the next page listener for vehicle
    class nextPageListenerVehicle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String messageOut = "";

            try {
                if (newUser) {
                    System.out.println("User Request");
                    messageOut = "user-request-vu" + "::" + username.getText() + "::" + password.getText();
                    outputStream.writeUTF(messageOut);
                }
                newUser = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
                if(inputStream.readUTF().equals("user-accept")){
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();

                    question1 = new JLabel("Do you want to submit a vehicle or see your previous information");
                    buttonData = new JButton("See your previous information");
                    buttonVehicle = new JButton("Submit a vehicle");

                    panel.add(question1);
                    panel.add(buttonData);
                    panel.add(buttonVehicle);
//                    panel.add(goBack);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            ActionListener newVehicle = new newVehicleListener();
            buttonVehicle.addActionListener(newVehicle);
        }
    }

    //This is asking for new job information
    class newJobListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
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
            ActionListener nextPageJob = new nextPageListenerJob();
            goBack.addActionListener(nextPageJob);
        }
    }

    //This creates a job
    class submitJobListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            String messageOut = "";

            try {
                System.out.println("sending a request job request");
                messageOut = "user-request-job::" + clientID.getText() + "::" + jobID.getText() + "::" + jobDuration.getText();

                if(!jobDeadline.getText().isEmpty())
                    messageOut += "::" + jobDeadline.getText();

                outputStream.writeUTF(messageOut);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(inputStream.readUTF().equals("job-accept")){
                    clientID.setText("");
                    jobID.setText("");
                    jobDuration.setText("");
                    jobDeadline.setText("");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

        //This is asking for new vehicle information
        class newVehicleListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
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
                ActionListener nextPageVehicle = new nextPageListenerVehicle();
                goBack.addActionListener(nextPageVehicle);
            }
        }

        //This creates a vehicle
        class submitVehicleListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {

                String messageOut = "";

                try {
                    messageOut = "user-request-vehicle:: " + ownerID.getText() + "::" + vehicleID.getText() + "::" + vehicleModel.getText() + "::" + vehicleMake.getText() + "::" + vehicleYear.getText();
                    System.out.println("Vehicle request.");
                    outputStream.writeUTF(messageOut);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try{
                    if(inputStream.readUTF().equals("vehicle-accept")){
                        ownerID.setText("");
                        vehicleID.setText("");
                        vehicleModel.setText("");
                        vehicleMake.setText("");
                        vehicleYear.setText("");
                    }else{
                        System.out.println("Vehicle has been rejected please try again");
                    }
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        }
}
