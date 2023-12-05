import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ControllerPage extends JFrame {
	private static final int FRAME_WIDTH = 750;
	private static final int FRAME_HEIGHT = 650;
	private JFrame controllerPage;
	private JButton completionTime;
	private JPanel panel;
	ClientConnection clientConnection;


	public ControllerPage() throws IOException {
		initGui();
		setupClient();
		new Thread(this :: listenForRequests).start();
	}

	public void initGui() throws IOException {

		controllerPage = new JFrame();
		controllerPage.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		controllerPage.setTitle("Welcome, Controller");
		controllerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		completionTime = new JButton("Calculate completion time");
		completionTime.setFont(new Font("Sans-Serif",Font.BOLD,16));

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = GridBagConstraints.REMAINDER;
		gbc.gridy = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;

		panel.add(completionTime, gbc);
		controllerPage.add(panel);

		controllerPage.revalidate();
		controllerPage.repaint();

		ActionListener calculate = new calculateTimeListener();
		completionTime.addActionListener(calculate);
		controllerPage.setVisible(true);
	}

	public void setupClient(){
		clientConnection = new ClientConnection("localhost",9806);
		try{
			clientConnection.connectToServer();
			clientConnection.sendMessage("controller");
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Unable to connect to the server.",
					"Connection Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void listenForRequests() {
		try {
			while (true) {
				String request = clientConnection.receiveMessage();
				String[] data = request.split("::");
				if ("request-confirmation".equals(data[0])) {
					int action = showConfirmationDialog(data);
					if (action == 0 ) {
						String accept = "accept";
						clientConnection.sendMessage(accept);
					}
					if(action == 1){
						String reject= "reject";
						clientConnection.sendMessage(reject);
					}
				}
				if("html".equals(data[0])){
					showProcessedData(data[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int showConfirmationDialog(String[] data) {
		// This will show a dialog with Yes and No options on the Event Dispatch Thread

		String message ="";
		if (data.length == 4) {
			message += "Client ID: " + data[1] + "<br>Job ID: " + data[2] + "<br>Job Duration:" + data[3];
		}
		if (data.length == 5) {
			message += "Client ID: " + data[1] + "<br>Job ID: " + data[2] + "<br>Job Duration:" + data[3] +"<br>Job Deadline: " +data[4];
		}
		if (data.length == 6) {
			message += "Owner ID: " + data[1] + "<br>Vehicle ID: " + data[2] + "<br>Vehicle Model: " + data[3] +"<br>Vehicle Make: " +data[4] + "<br>Vehicle Year: " + data[5];
		}
		JLabel temp = new JLabel("<html>Do you want to accept the following request:<br>"+message +"</html>");

		return JOptionPane.showOptionDialog(
				controllerPage,
				temp,
				"Request Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				null,
				null
		);
	}

	private void showProcessedData(String data){

		JFrame jobCompletionController = new JFrame();
		jobCompletionController.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jobCompletionController.setTitle("Controller list of current completed jobs' ID and completion time");
		jobCompletionController.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jobCompletionController.setVisible(true);

		JPanel newPanel = new JPanel();
		newPanel.add(new JLabel(data));
		jobCompletionController.add(newPanel);
		jobCompletionController.revalidate();
		jobCompletionController.repaint();
	}

	//This is the action  listener for calculation complete time within controller
	class calculateTimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			try {
				clientConnection.sendMessage("process-jobs");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}


		}
	}
}
