import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vehicleUserGui {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    JFrame vehicleView= new JFrame();
    JPanel panel = new JPanel();
    JLabel question1 = new JLabel("Do you want to submit a vehicle or see your previous information");
    JButton buttonData = new JButton("See your previous information");
    JButton buttonVehicle = new JButton("Submit a vehicle");
    ClientConnection clientConnection;
    public vehicleUserGui(ClientConnection connection) {
        clientConnection = connection;
        buttonVehicle.addActionListener(new goToVehicleCreationGui());
        panel.add(question1);
        panel.add(buttonData);
        panel.add(buttonVehicle);
        vehicleView.add(panel);
        vehicleView.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        vehicleView.setTitle("New User");
        vehicleView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vehicleView.setVisible(true);
    }

    class goToVehicleCreationGui implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            new vehicleCreationGui(clientConnection);
            System.gc();
        }
    }
}
