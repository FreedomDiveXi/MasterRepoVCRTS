import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ControllerPage extends JFrame {
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 500;
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
		controllerPage.setVisible(true);

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
				if ("request-confirmation".equals(request)) {
					int action = showConfirmationDialog();
					if (action == 0 ) {
						String accept = "accept";
						clientConnection.sendMessage(accept);
					}
					if(action == 1){
						String reject= "reject";
						clientConnection.sendMessage(reject);
					}
				}
				if(request.contains("html")){
					showProcessedData(request);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int showConfirmationDialog() {
		// This will show a dialog with Yes and No options on the Event Dispatch Thread
		return JOptionPane.showOptionDialog(
				controllerPage,
				"Do you want to accept the request?",
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
