import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ControllerPage extends JFrame {
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 500;
	private JFrame controllerPage;
	private JButton completionTime;
	private JPanel panel;
	private String messageOut;
	JPanel requestPanel;
	JButton accept;
	JButton reject;
	ClientConnection clientConnection;


	public ControllerPage() throws IOException {
		initGui();
		setupClient();
	}

	public void initGui() throws IOException {

		controllerPage = new JFrame();
		controllerPage.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		controllerPage.setTitle("Welcome to the Controller");
		controllerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		controllerPage.setVisible(true);

		// simple server

		completionTime = new JButton("Calculate completion time");

		panel = new JPanel();
		panel.add(completionTime);
		controllerPage.add(panel);
		controllerPage.revalidate();
		controllerPage.repaint();

		ActionListener calculate = new calculateTimeListener();
		completionTime.addActionListener(calculate);
	}

	public void setupClient(){
		clientConnection = new ClientConnection("localhost",9806);
		try{
			clientConnection.connectToServer();
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Unable to connect to the server.",
					"Connection Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	//This is the action  listener for calculation complete time within controller
	class calculateTimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JFrame jobCompletionController = new JFrame();
			panel = new JPanel();

			String str = "<html>";
//			str += run.startProcessing();
			str += "</html>";

			panel.add(new JLabel(str));
			jobCompletionController.add(panel);
			jobCompletionController.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			jobCompletionController.setTitle("Controller list of current completed jobs' ID and completion time");
			jobCompletionController.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jobCompletionController.setVisible(true);

		}
	}
}
