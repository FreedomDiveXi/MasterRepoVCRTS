import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class vehicleCreationGui {
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 500;
    JFrame SubmitVehicle = new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Owner ID");
    JTextField ownerID = new JTextField(50);
    JLabel question2 = new JLabel("Vehicle ID");
    JTextField vehicleID = new JTextField(50);
    JLabel question3 = new JLabel("What is your vehicle's model?");
    JTextField vehicleModel = new JTextField(50);
    JLabel question4 = new JLabel("What is your vehicle's make/brand?");
    JTextField vehicleMake = new JTextField(50);
    JLabel question5 = new JLabel("What is your vehicle's year");
    JTextField vehicleYear = new JTextField(50);
    JButton submitVehicle = new JButton("Submit");
    ClientConnection clientConnection;
    JLabel temp1 = new JLabel("");

    public vehicleCreationGui(ClientConnection connection){
        listenForRequests();
        clientConnection = connection;
        question1.setHorizontalAlignment(JLabel.CENTER);
    	question2.setHorizontalAlignment(JLabel.CENTER);
    	question3.setHorizontalAlignment(JLabel.CENTER);
    	question4.setHorizontalAlignment(JLabel.CENTER);
    	question5.setHorizontalAlignment(JLabel.CENTER);
    	panel.setLayout(new GridLayout(6, 2, 5, 45));
        
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
        panel.add(temp1);
        panel.add(submitVehicle);
        SubmitVehicle.add(panel);
        SubmitVehicle.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        SubmitVehicle.setTitle("Enlisting Multiple Vehicles");
        SubmitVehicle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SubmitVehicle.setVisible(true);

        submitVehicle.addActionListener(new submitVehicleListener());
    }

    public void listenForRequests(){
        new Thread(()->{
            while(true){
                try{
                    String request = clientConnection.receiveMessage();
                    processServerResponse(request);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    private void processServerResponse(String request) {
        SwingUtilities.invokeLater(()->{
            if("accepted-vehicle".contains(request)){
                ownerID.setText("");
                vehicleID.setText("");
                vehicleModel.setText("");
                vehicleMake.setText("");
                vehicleYear.setText("");
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(submitVehicle,"Vehicle submission has been accepted.", "Accepted", JOptionPane.INFORMATION_MESSAGE);
                });
            }else if ("rejected-vehicle".contains(request)){
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(submitVehicle,"Vehicle submission was rejected. Please try again.", "Rejection", JOptionPane.INFORMATION_MESSAGE);
                });
            }
        });
    }
    //This creates a vehicle
    class submitVehicleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                String messageOut = "user-request-vehicle:: " + ownerID.getText() + "::" + vehicleID.getText() + "::" + vehicleModel.getText() + "::" + vehicleMake.getText() + "::" + vehicleYear.getText();
                clientConnection.sendMessage(messageOut);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
