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
	CloudController run = new CloudController();
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	private String messageOut;
	JPanel requestPanel;
	JButton accept;
	JButton reject;

	private JobOwner currentJobOwner;
	private VehicleOwner currentVehicleOwner;

	public ControllerPage() throws IOException {
		initGui();
		startServer();
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

	public void startServer(){
		String messageIn = "";
		System.out.println("server is running");
		try {
			serverSocket = new ServerSocket(9806);
			socket = serverSocket.accept();
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());

			// will read the things coming from client.
			while (!messageIn.equals("done")) {
				System.out.println("counting");
				messageIn = inputStream.readUTF();
				String[] parts = messageIn.split("::");

				// job user
				if (parts[0].equals("user-request-ju")) {
					run.createJobOwner(parts[1], parts[2]);
					outputStream.writeUTF("user-accept");
				}

				// vehicle user
				if (parts[0].equals("user-request-vu")) {
					run.createVehicleOwner(parts[1], parts[2]);
					outputStream.writeUTF("user-accept");
				}
				// job
				if (parts[0].equals("user-request-job")) {
					int result = JOptionPane.showOptionDialog(
							null,
							"Would you like to accept the incoming job?",
							"INCOMING JOB REQUEST!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null,
							null,
							null
					);
					handleJobRequest(result, parts);
				}
				// vehicle
				if (parts[0].equals("user-request-vehicle")) {

					int result = JOptionPane.showOptionDialog(
							null,
							"Would you like to accept the incoming vehicle?",
							"INCOMING VEHICLE REQUEST!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null,
							null,
							null
					);
					handleVehicleRequest(result, parts);
				}
			}

		} catch (Exception e) {
			System.out.println("Failure from server");
			e.printStackTrace();
		}
	}
	public void handleJobRequest(int result,String[] request) {
		if(result == JOptionPane.YES_OPTION){
			// deadline check
			if(request.length == 4)
				run.createJob(request[1],request[2],request[3]);
			if(request.length == 5)
				run.createJob(request[1],request[2],request[3],request[4]);

			System.out.println(run.acceptJob());

			try {
				outputStream.writeUTF("job-accept");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		if(result == JOptionPane.NO_OPTION){
			// deadline check
			if(request.length == 4)
				run.createJob(request[1],request[2],request[3]);
			if(request.length == 5)
				run.createJob(request[1],request[2],request[3],request[4]);

			System.out.println(run.rejectJob());
		}
	}

	public void handleVehicleRequest(int result, String[] request) {
		if(result == JOptionPane.YES_OPTION){
			run.createVehicle(request[1],request[2],request[3],request[4],request[5]);
			System.out.println(run.acceptVehicle());
			try {
				outputStream.writeUTF("vehicle-accept");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		if(result == JOptionPane.NO_OPTION){
			run.createVehicle(request[1],request[2],request[3],request[4],request[5]);
			System.out.println(run.rejectVehicle());
		}
	}

	//This is the action  listener for calculation complete time within controller
	class calculateTimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JFrame jobCompletionController = new JFrame();
			panel = new JPanel();

			String str = "<html>";
			str += run.startProcessing();
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
