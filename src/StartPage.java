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

    public StartPage() throws IOException{
        initGui();
    }

    //This is the constructor as well as the starting point to the objects inside the main JFrame
    public void initGui() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Welcome, Client");
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


    //This is the home page listener
    class HomePageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            panel.setLayout(new GridLayout(5, 3, 0, 50));
            
            question1 = new JLabel("Are you a new user?");
            question1.setHorizontalAlignment(JLabel.CENTER);
            buttonYes = new JButton("Yes");
            buttonNo = new JButton("No");
            JLabel temp1 = new JLabel("");
            JLabel temp2 = new JLabel("");
            JLabel temp3 = new JLabel("");
            JLabel temp4 = new JLabel("");
            JLabel temp5 = new JLabel("");
            JLabel temp6 = new JLabel("");
            JLabel temp7 = new JLabel("");
            JLabel temp8 = new JLabel("");
            JLabel temp9 = new JLabel("");
            JLabel temp10 = new JLabel("");
            JLabel temp11 = new JLabel("");
            JLabel temp12 = new JLabel("");
            
            panel.add(temp1);
            panel.add(temp2);
            panel.add(temp3);
            panel.add(temp4);
            panel.add(question1);
            panel.add(temp5);
            panel.add(temp6);
            panel.add(buttonYes);
            panel.add(temp7);
            panel.add(temp8);
            panel.add(buttonNo);
            panel.add(temp9);
            panel.add(temp10);
            panel.add(temp11);
            panel.add(temp12);

            buttonYes.addActionListener(new AddNewUserListener());
        }
    }

    //This is the action caller for new users to register
    class AddNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            new Thread(userLoginGui::new).start();
        }
    }
}
