import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vehicleUserGui {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame vehicleView= new JFrame();
    JPanel main = new JPanel();
    JPanel header = new JPanel();
    JPanel body = new JPanel();
    JPanel footer = new JPanel();
    JLabel welcomeMessage = new JLabel("Got a new vehicle?");
    JButton vehicleButton = new JButton("Submit a vehicle");
    JLabel historicalMessage= new JLabel("Vehicle Dashboard");
    JButton historyButton= new JButton("See Vehicles");
    ClientConnection clientConnection;
    public vehicleUserGui(ClientConnection connection) {
        clientConnection = connection;
        vehicleButton.addActionListener(new goToVehicleCreationGui());
        guiFormatInit();

        JPanel newThing = new JPanel(new GridLayout(1,2));

        JPanel leftColumnPanel = new JPanel();
        leftColumnPanel.setLayout(new BoxLayout(leftColumnPanel, BoxLayout.Y_AXIS));
        welcomeMessage.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        vehicleButton.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        vehicleButton.setPreferredSize(new Dimension(170, 50));
        buttonPanel.add(vehicleButton);

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
        historyButton.setPreferredSize(new Dimension(150, 50));
        buttonPanel1.add(historyButton);


        rightColumnPanel.add(historicalMessage);
        rightColumnPanel.add(Box.createVerticalStrut(10));
        rightColumnPanel.add(buttonPanel1);

        newThing.add(leftColumnPanel);
        newThing.add(rightColumnPanel);
        body.add(new JLabel(""));
        body.add(newThing);
        body.add(new JLabel(""));

        vehicleView.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        vehicleView.setTitle("Vehicle App");
        vehicleView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vehicleView.setVisible(true);

    }

    public void guiFormatInit(){
        main.setLayout(new BorderLayout());
        body.setLayout(new GridLayout(3,1));

        main.add(header,BorderLayout.NORTH);
        main.add(body,BorderLayout.CENTER);
        main.add(footer,BorderLayout.SOUTH);
        vehicleView.add(main);
    }

    class goToVehicleCreationGui implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            new vehicleCreationGui(clientConnection);
            System.gc();
        }
    }
}
