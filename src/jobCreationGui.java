import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class jobCreationGui {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame SubmitJob = new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Client ID");
    JTextField clientID = new JTextField(50);
    JLabel question2 = new JLabel("Job ID");
    JTextField jobID = new JTextField(50);
    JLabel question3 = new JLabel("Approximately how long will this job take, please just type in the total amount of hours?");
    JTextField jobDuration = new JTextField(50);
    JLabel question4 = new JLabel("If needed what is this job's deadline, please type in this format month-day-year");
    JTextField jobDeadline = new JTextField(50);
    JButton submitJob = new JButton("Submit");
    ClientConnection clientConnection;
    public jobCreationGui(ClientConnection connection){
        clientConnection = connection;
        panel.add(question1);
        panel.add(clientID);
        panel.add(question2);
        panel.add(jobID);
        panel.add(question3);
        panel.add(jobDuration);
        panel.add(question4);
        panel.add(jobDeadline);
        panel.add(submitJob);
        SubmitJob.add(panel);
        SubmitJob.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        SubmitJob.setTitle("Enlisting Multiple Jobs");
        SubmitJob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SubmitJob.setVisible(true);

       submitJob.addActionListener(new submitJobListener());
       new Thread(this::listenForRequests).start();
    }

    public void listenForRequests(){
        while(true){
            try{
                String request = clientConnection.receiveMessage();

                if("accepted-job".equals(request))
                    clearJobFields();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearJobFields(){
        clientID.setText("");
        jobID.setText("");
        jobDuration.setText("");
        jobDeadline.setText("");
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
