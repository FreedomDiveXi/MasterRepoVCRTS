import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jobUserGui {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame jobView = new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Do you want to submit a job or see your previous information");
    JButton buttonData = new JButton("See your previous information");
    JButton buttonJob = new JButton("Submit a job");
    ClientConnection clientConnection;
    public jobUserGui(ClientConnection connection) {
        clientConnection = connection;
        buttonJob.addActionListener(new goToJobCreationGui());
        panel.add(question1);
        panel.add(buttonData);
        panel.add(buttonJob);

        jobView.add(panel);
        jobView.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        jobView.setTitle("New User");
        jobView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jobView.setVisible(true);
    }

    class goToJobCreationGui implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new jobCreationGui(clientConnection);
            System.gc();
        }
    }
}
