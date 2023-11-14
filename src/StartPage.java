import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StartPage extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private JLabel introduction, question1;
    private JButton buttonYes, buttonNo, goNext;
    private JPanel panel;
    private ClientConnection clientConnection;

    public StartPage() throws IOException{
        initGui();
        setupClient();
    }

    //This is the constructor as well as the starting point to the objects inside the main JFrame
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
        panel.add(goNext);

        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        goNext.addActionListener(new HomePageListener());
    }

    public void setupClient(){
        clientConnection = new ClientConnection("localhost",9806);
        try{
            clientConnection.connectToServer();
            clientConnection.sendMessage("client");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This is the home page listener
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
        }
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            new userLoginGui(clientConnection);
        }
    }
}
