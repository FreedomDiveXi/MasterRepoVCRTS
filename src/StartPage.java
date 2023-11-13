import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StartPage extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private JLabel introduction, question1, question2, question3, question4, question5;
    private JButton jobOwnerButton, vehicleOwnerButton, buttonYes, buttonNo, buttonData, buttonJob, buttonVehicle, submitJob, submitVehicle, goNext, goBack;
    private JPanel panel, panel2;
    private JTextField username, clientID, jobID, jobDuration, jobDeadline, ownerID, vehicleID, vehicleModel, vehicleMake, vehicleYear;
    private JPasswordField password;
    private Boolean newUser;

    private ClientConnection clientConnection; 

    public StartPage() throws IOException {
        initGui();
        setupClient();
    }

    public void initGui() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Welcome to the Controller");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        introduction = new JLabel("<html><body>" +
                                  "This application allows users to complete certain tasks that would require " +
                                  "an immense amount of power that you simply do not have, or input your own " +
                                  "unoccupied car, so we can utilize the computational power that a car has." +
                                  "</body></html>");
        introduction.setHorizontalAlignment(JLabel.CENTER);

        introduction.setMaximumSize(new Dimension(FRAME_WIDTH - 50, Integer.MAX_VALUE));
        introduction.setPreferredSize(new Dimension(FRAME_WIDTH - 50, 100));

        goNext = new JButton("Continue");

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.add(introduction, gbc);
        panel.add(goNext, gbc);

        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        goNext.addActionListener(new HomePageListener());
    }




    public void setupClient() {
        clientConnection = new ClientConnection("localhost", 9806);
        try {
            clientConnection.connectToServer();
            clientConnection.sendMessage("client");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to the server.",
                                          "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearVehicleFields() {
        ownerID.setText("");
        vehicleID.setText("");
        vehicleModel.setText("");
        vehicleMake.setText("");
        vehicleYear.setText("");
    }

    private void clearJobFields() {
        clientID.setText("");
        jobID.setText("");
        jobDuration.setText("");
        jobDeadline.setText("");
    }

    class HomePageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panel.removeAll();
            panel.setLayout(new GridLayout(0, 1));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));

            question1 = new JLabel("Are you a new user?");
            buttonYes = new JButton("Yes");
            buttonNo = new JButton("No");

            panel.add(question1);
            panel.add(buttonYes);
            panel.add(buttonNo);

            buttonYes.addActionListener(new AddNewUserListener());

            panel.revalidate();
            panel.repaint();
        }
    }

    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JFrame userLogin = new JFrame();
            JPanel panel = new JPanel();
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

            userLogin.add(panel);
            userLogin.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            userLogin.setTitle("New User");
            userLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            userLogin.setVisible(true);

            jobOwnerButton.addActionListener(new userIsJobOwnerListener());
            vehicleOwnerButton.addActionListener(new userIsVehicleOwnerListener());
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
            String messageOut = "user-request-ju" + "::" + username.getText() + "::" + password.getText();
            try {
                if (newUser) {
                    clientConnection.sendMessage(messageOut);
                    newUser = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(clientConnection.receiveMessage().equals("user-accept")){
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();

                    question1 = new JLabel("Do you want to submit a job or see your previous information");
                    buttonData = new JButton("See your previous information");
                    buttonJob = new JButton("Submit a job");

                    buttonJob.addActionListener(new newJobListener());
                    panel.add(question1);
                    panel.add(buttonData);
                    panel.add(buttonJob);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //This is the next page listener for vehicle
    class nextPageListenerVehicle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String messageOut = "user-request-vu" + "::" + username.getText() + "::" + password.getText();

                try {
                    if (newUser) {
                        clientConnection.sendMessage(messageOut);
                        newUser = false;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            try {
                while(true){
                    if (clientConnection.receiveMessage().equals("user-accept")) {

                        panel.removeAll();
                        question1 = new JLabel("Do you want to submit a vehicle or see your previous information");
                        buttonData = new JButton("See your previous information");
                        buttonVehicle = new JButton("Submit a vehicle");

                        buttonVehicle.addActionListener(new newVehicleListener());
                        panel.add(question1);
                        panel.add(buttonData);
                        panel.add(buttonVehicle);
//                        panel.add(goBack);
                        panel.revalidate();
                        panel.repaint();
                        break;
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

            String messageOut = "user-request-job::" + clientID.getText() + "::" + jobID.getText() + "::" + jobDuration.getText();
            try {
                if(!jobDeadline.getText().isEmpty())
                    messageOut += "::" + jobDeadline.getText();
                clientConnection.sendMessage(messageOut);
            } catch (Exception e) {
                e.printStackTrace();
            }

            new Thread(()->{
                try {
                    if(clientConnection.receiveMessage().equals("accepted-job"))
                        clearJobFields();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();

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
                try {
                    String messageOut = "user-request-vehicle:: " + ownerID.getText() + "::" + vehicleID.getText() + "::" + vehicleModel.getText() + "::" + vehicleMake.getText() + "::" + vehicleYear.getText();
                    clientConnection.sendMessage(messageOut);

                    new Thread(()->{
                        try {
                            if(clientConnection.receiveMessage().equals("accepted-vehicle"))
                                clearVehicleFields();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}
