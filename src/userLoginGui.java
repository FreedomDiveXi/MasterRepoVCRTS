import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class userLoginGui {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame userLogin = new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Create a new username (':' is not allowed): ");
    JTextField username = new JTextField(50);
    JLabel question2 = new JLabel("Create a new password: ");
    JPasswordField password = new JPasswordField(50);
    JLabel question3 = new JLabel("Are you a job owner or vehicle owner? Choose only one.");
    JButton jobOwnerButton = new JButton("Job Owner");
    JButton vehicleOwnerButton = new JButton("Vehicle Owner");
    JLabel question4 = new JLabel();
    JButton isJobOwner = new JButton("Continue");
    JButton isVehicleOwner= new JButton("Continue");
    ClientConnection clientConnection;

    public userLoginGui(ClientConnection connection){
        clientConnection = connection;
        panel.add(question1);
        panel.add(username);
        panel.add(question2);
        panel.add(password);
        panel.add(question3);
        panel.add(jobOwnerButton);
        panel.add(vehicleOwnerButton);
        userLogin.add(panel);
        userLogin.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        userLogin.setTitle("New User");
        userLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userLogin.setVisible(true);


        jobOwnerButton.addActionListener(new userIsJobOwnerListener());
        vehicleOwnerButton.addActionListener(new userIsVehicleOwnerListener());
        isJobOwner.addActionListener(new goToJobView());
        isVehicleOwner.addActionListener(new goToVehicleView());
    }
    //This is the button to make the user a job owner
    class userIsJobOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panel.add(question4);
            panel.add(isJobOwner);
            question4.setText("You have selected job owner.");
            panel.revalidate();
            panel.repaint();
            System.gc();
        }
    }

    //This is the button to make the user a vehicle owner
    class userIsVehicleOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panel.add(question4);
            panel.add(isVehicleOwner);
            question4.setText("You have selected vehicle owner.");
            panel.revalidate();
            panel.repaint();
            System.gc();
        }
    }

    class goToJobView implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            // instead of creating the object here, we funnel the response to the server so that
            // the server can create it.
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
