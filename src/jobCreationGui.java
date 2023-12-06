import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class jobCreationGui {
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 500;
    JFrame SubmitJob = new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Client ID");
    JTextField clientID = new JTextField(25);
    JLabel question2 = new JLabel("Job ID");
    JTextField jobID = new JTextField(25);
    JLabel question3 = new JLabel("<html><div style = 'text-align:center;'>" + 
    								"Approximately how long will this job take, " +
    								"please type a number representing the amount of hours." + 
    								"</div></html>");
    JTextField jobDuration = new JTextField(25);
    JLabel question4 = new JLabel("<html><div style = 'text-align:center;'>" + 
    								"If needed what is this job's deadline, " +
    								"please type in this format year-month-day" + 
    								"</div></html>");
    JTextField jobDeadline = new JTextField(25);
    JButton submitJob = new JButton("Submit");
    ClientConnection clientConnection;
    JLabel temp1 = new JLabel("");
    
    public jobCreationGui(ClientConnection connection){
        listenForRequests();
        clientConnection = connection;
        question1.setHorizontalAlignment(JLabel.CENTER);
    	question2.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(5, 2, 5, 65));
        
        panel.add(question1);
        panel.add(clientID);
        panel.add(question2);
        panel.add(jobID);
        panel.add(question3);
        panel.add(jobDuration);
        panel.add(question4);
        panel.add(jobDeadline);
        panel.add(temp1);
        panel.add(submitJob);
        SubmitJob.add(panel);
        SubmitJob.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        SubmitJob.setTitle("Enlisting Multiple Jobs");
        SubmitJob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SubmitJob.setVisible(true);

       submitJob.addActionListener(new submitJobListener());
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

    private void processServerResponse(String request){
        SwingUtilities.invokeLater(()->{
            if("accepted-job".contains(request)) {
                clientID.setText("");
                jobID.setText("");
                jobDuration.setText("");
                jobDeadline.setText("");
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(SubmitJob,"Job submission has been accepted.", "Accepted", JOptionPane.INFORMATION_MESSAGE);
                });
            }else if ("rejected-job".contains(request)){
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(SubmitJob,"Job submission was rejected. Please try again.", "Rejection", JOptionPane.INFORMATION_MESSAGE);
                });
            }
        });
    }
    class submitJobListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String messageOut = "user-request-job::" + clientID.getText() + "::" + jobID.getText() + "::" + jobDuration.getText();
            try {
                if(!jobDeadline.getText().isEmpty())
                    messageOut += "::" + jobDeadline.getText();
                clientConnection.sendMessage(messageOut);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
