import javax.swing.*;
import java.awt.*;

public class VehicleOwnerView extends JFrame {
    
    JPanel mainFrame;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JButton newVehicleButton; // Corrected the spelling of "Vehicle"
    private JPanel innerCenterBody;
    private JButton activeJobsButton;
    private JButton historyButton;
    private JButton findAJobButton;
    private JButton exportDataButton;
    private JLabel headerTextTop;
    private JLabel headerTextMiddle;
    private JLabel headerTextBottom;

    public VehicleOwnerView() {
        setTitle("Vehicle Owner Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize all the components
        mainFrame = new JPanel(new BorderLayout());

        header = new JPanel();
        body = new JPanel(new BorderLayout());
        footer = new JPanel();

        newVehicleButton = new JButton("New Vehicle");
        innerCenterBody = new JPanel();
        activeJobsButton = new JButton("Active Jobs");
        historyButton = new JButton("History");
        findAJobButton = new JButton("Find a Job");
        exportDataButton = new JButton("Export Data");

        headerTextTop = new JLabel("Welcome to the");
        headerTextMiddle = new JLabel("Vehicle Owner Dashboard");
        headerTextBottom = new JLabel("Manage your vehicles and jobs here.");

        // Set up the layout and add components
        header.setLayout(new GridLayout(3,1));
        header.add(headerTextTop);
        header.add(headerTextMiddle);
        header.add(headerTextBottom);

        innerCenterBody.setLayout(new GridLayout(4, 1, 10, 10));
        innerCenterBody.add(newVehicleButton);
        innerCenterBody.add(activeJobsButton);
        innerCenterBody.add(historyButton);
        innerCenterBody.add(findAJobButton);

        body.add(innerCenterBody, BorderLayout.CENTER);
        footer.add(exportDataButton);

        mainFrame.add(header, BorderLayout.NORTH);
        mainFrame.add(body, BorderLayout.CENTER);
        mainFrame.add(footer, BorderLayout.SOUTH);

        this.add(mainFrame);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicleOwnerView view = new VehicleOwnerView();
            view.setVisible(true);
        });
    }
}
