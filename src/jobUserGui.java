import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jobUserGui {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame jobView = new JFrame();
    JPanel main = new JPanel();
    JPanel header = new JPanel();
    JPanel body = new JPanel();
    JPanel footer = new JPanel();
    JLabel welcomeMessage = new JLabel("Got a new job?");
    JButton jobButton = new JButton("Submit a job");
    JLabel historicalMessage= new JLabel("See history");
    JButton historyButton= new JButton("History");
    ClientConnection clientConnection;
    public jobUserGui(ClientConnection connection) {
        clientConnection = connection;
        jobButton.addActionListener(new goToJobCreationGui());
        guiFormatInit();

        JPanel newThing = new JPanel(new GridLayout(1,2));

        JPanel leftColumnPanel = new JPanel();
        leftColumnPanel.setLayout(new BoxLayout(leftColumnPanel, BoxLayout.Y_AXIS));
        welcomeMessage.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jobButton.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        jobButton.setPreferredSize(new Dimension(130, 50));
        buttonPanel.add(jobButton);

        // Add components to leftColumnPanel
        leftColumnPanel.add(welcomeMessage);
        leftColumnPanel.add(Box.createVerticalStrut(10));
        leftColumnPanel.add(buttonPanel);

        JPanel rightColumnPanel = new JPanel();
        rightColumnPanel.setLayout(new BoxLayout(rightColumnPanel, BoxLayout.Y_AXIS));
        historicalMessage.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        historicalMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        historyButton.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        historyButton.setPreferredSize(new Dimension(130, 50));
        buttonPanel1.add(historyButton);


        rightColumnPanel.add(historicalMessage);
        rightColumnPanel.add(Box.createVerticalStrut(10));
        rightColumnPanel.add(buttonPanel1);

        newThing.add(leftColumnPanel);
        newThing.add(rightColumnPanel);
        body.add(new JLabel(""));
        body.add(newThing);
        body.add(new JLabel(""));

        jobView.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        jobView.setTitle("Job App");
        jobView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jobView.setVisible(true);
    }

    public void guiFormatInit(){
        main.setLayout(new BorderLayout());
        body.setLayout(new GridLayout(3,1));

        main.add(header,BorderLayout.NORTH);
        main.add(body,BorderLayout.CENTER);
        main.add(footer,BorderLayout.SOUTH);
        jobView.add(main);
    }

    class goToJobCreationGui implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new jobCreationGui(clientConnection);
            System.gc();
        }
    }
}
