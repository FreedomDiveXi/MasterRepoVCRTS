import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class userLoginGui {
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 500;
    JFrame userLogin = new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Create a new username: ");
    JTextField username = new JTextField(25);
    JLabel question2 = new JLabel("Create a new password: ");
    JPasswordField password = new JPasswordField(25);
    JLabel question3 = new JLabel("Are you a job owner or vehicle owner?");
    JButton jobOwnerButton = new JButton("Job Owner");
    JButton vehicleOwnerButton = new JButton("Vehicle Owner");
    JLabel question4 = new JLabel("Please select a choice to move forward.");
    JButton goNext = new JButton("Continue");
    ClientConnection clientConnection;
    JLabel temp1 = new JLabel("");
    JLabel temp2 = new JLabel("");
    JLabel temp3 = new JLabel("");

    public userLoginGui(){
    	question1.setHorizontalAlignment(JLabel.CENTER);
    	question2.setHorizontalAlignment(JLabel.CENTER);
    	question3.setHorizontalAlignment(JLabel.CENTER);
    	question4.setHorizontalAlignment(JLabel.CENTER);
    	panel.setLayout(new GridLayout(4, 3, 5, 75));
    	
        panel.add(question1);
        panel.add(username);
        panel.add(temp1);
        panel.add(question2);
        panel.add(password);
        panel.add(temp2);
        panel.add(question3);
        panel.add(jobOwnerButton);
        panel.add(vehicleOwnerButton);
        panel.add(question4);
        panel.add(goNext);
        panel.add(temp3);
        userLogin.add(panel);
        userLogin.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        userLogin.setTitle("New User");
        userLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userLogin.setVisible(true);

        jobOwnerButton.addActionListener(new userIsJobOwnerListener());
        vehicleOwnerButton.addActionListener(new userIsVehicleOwnerListener());
    }
    public void setupClient(String id){
        clientConnection = new ClientConnection("localhost",9806);
        try{
            clientConnection.connectToServer();
            clientConnection.sendMessage(id);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    //This is the button to make the user a job owner
    class userIsJobOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	panel.revalidate();
            panel.repaint();
            question4.setText("You have selected job owner.");
            goNext.addActionListener(new goToJobView());
            System.gc();
        }
    }

    //This is the button to make the user a vehicle owner
    class userIsVehicleOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	panel.revalidate();
            panel.repaint();
            question4.setText("You have selected vehicle owner.");
            goNext.addActionListener(new goToVehicleView());
            System.gc();
        }
    }

    class goToJobView implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            // da solution to our problems, JUST MAKE A SEPARATE CONNECTION IF YOU DONT WANT RACE CONDITIONS
            setupClient("job-user");
            String messageOut = "user-request-ju" + "::" + username.getText() + "::" + password.getText();
            try {
                clientConnection.sendMessage(messageOut);

                if (clientConnection.receiveMessage().equals("user-accept")) {
                    new jobUserGui(clientConnection);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userLogin.dispose();
            System.gc();
        }
    }

    class goToVehicleView implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            // da solution to our problems, JUST MAKE A SEPARATE CONNECTION IF YOU DONT WANT RACE CONDITIONS
            setupClient("vehicle-user");
            String messageOut = "user-request-vu" + "::" + username.getText() + "::" + password.getText();

            try {
                clientConnection.sendMessage(messageOut);
                if (clientConnection.receiveMessage().equals("user-accept")) {
                    new vehicleUserGui(clientConnection);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userLogin.dispose();
            System.gc();
        }
    }
}
