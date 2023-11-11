import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class StartPage extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame start;
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

    private ClientConnection clientConnection;

    public StartPage() throws IOException{
        initGui();
        setupClient();
    }

    //This is the constructor as well as the starting point to the objects inside the main JFrame
    public void initGui() throws IOException {
        start = new JFrame();
        start.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        start.setTitle("Welcome to the Controller");
        start.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        start.setVisible(true);

        introduction = new JLabel("<html>" + "This application allows users to complete certain tasks that would require " + "<br/>" + "an immense amount of power that you simply do not have or input your own " + "<br/>" + "unoccupied car, so we can utilize the computational power that a car has." + "</html>");
        goNext = new JButton("Continue");

        panel = new JPanel();
        panel.add(introduction);
        panel.add(goNext);
        start.add(panel);

        ActionListener homePage = new homePageListener();
        goNext.addActionListener(homePage);
    }

    public void setupClient(){
        clientConnection = new ClientConnection("localhost",9806);
        try{
            clientConnection.connectToServer();
            System.out.println("able to connect to server! coming from actual client");
        }catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to the server.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
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

            panel.add(question1);
            panel.add(buttonYes);
            panel.add(buttonNo);

            ActionListener yesListener = new AddNewUserListener();
            buttonYes.addActionListener(yesListener);
        }
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JFrame UserLogin = new JFrame();
            panel = new JPanel();
            newUser = true;

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
            UserLogin.add(panel);
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
//                    outputStream.writeUTF(messageOut);
                }
                newUser = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

//            try {
//                if(inputStream.readUTF().equals("user-accept")){
//                    System.out.println("user has been accepted");
//                    panel.removeAll();
//                    panel.revalidate();
//                    panel.repaint();
//
//                    question1 = new JLabel("Do you want to submit a job or see your previous information");
//                    buttonData = new JButton("See your previous information");
//                    buttonJob = new JButton("Submit a job");
//
//                    panel.add(question1);
//                    panel.add(buttonData);
//                    panel.add(buttonJob);
//
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
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
//                    outputStream.writeUTF(messageOut);
                }
                newUser = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

//            try{
//                if(inputStream.readUTF().equals("user-accept")){
//                    panel.removeAll();
//                    panel.revalidate();
//                    panel.repaint();
//
//                    question1 = new JLabel("Do you want to submit a vehicle or see your previous information");
//                    buttonData = new JButton("See your previous information");
//                    buttonVehicle = new JButton("Submit a vehicle");
//
//                    panel.add(question1);
//                    panel.add(buttonData);
//                    panel.add(buttonVehicle);
////                    panel.add(goBack);
//                }
//            }catch(IOException e){
//                e.printStackTrace();
//            }
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

//                outputStream.writeUTF(messageOut);
            } catch (Exception e) {
                e.printStackTrace();
            }

//            try {
//                if(inputStream.readUTF().equals("job-accept")){
//                    clientID.setText("");
//                    jobID.setText("");
//                    jobDuration.setText("");
//                    jobDeadline.setText("");
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
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
//                    outputStream.writeUTF(messageOut);

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                try{
//                    if(inputStream.readUTF().equals("vehicle-accept")){
//                        ownerID.setText("");
//                        vehicleID.setText("");
//                        vehicleModel.setText("");
//                        vehicleMake.setText("");
//                        vehicleYear.setText("");
//                    }else{
//                        System.out.println("Vehicle has been rejected please try again");
//                    }
//                }catch (IOException e){
//                    throw new RuntimeException(e);
//                }
            }
        }
}