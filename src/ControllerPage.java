import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ControllerPage extends JFrame{
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 500;
	private JButton completionTime;
	private JButton acceptReject;
	private JButton accept;
	private JButton reject;
	private JPanel panel;
	private JPanel panel2;
    CloudController run = new CloudController();
    static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	private String messageOut;
	
	public ControllerPage() throws IOException {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		completionTime = new JButton("Calculate completion time");
		acceptReject = new JButton("Accept/Reject Authorization");
		
		panel = new JPanel();
		panel.add(completionTime);
		panel.add(acceptReject);
		add(panel);
		
		ActionListener calculate = new calculateTimeListener();
		completionTime.addActionListener(calculate);
		ActionListener acceptRejectListen = new acceptRejectListener();
		acceptReject.addActionListener(acceptRejectListen);
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
    
    class acceptRejectListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		JFrame acceptReject = new JFrame();
    		panel = new JPanel();
    		
    		accept = new JButton("Accept");
    		reject = new JButton("Reject");
    		String messageIn = "";
    		Scanner keyInput;
    		
    		try {
    			serverSocket = new ServerSocket(9806);
    			socket = serverSocket.accept();
    			inputStream = new DataInputStream(socket.getInputStream());
    			outputStream = new DataOutputStream(socket.getOutputStream());
    			
    			messageIn = inputStream.readUTF();
    			JFrame userInput = new JFrame();
    			panel2 = new JPanel();
    			panel2.add(new JLabel(messageIn));
    			userInput.add(panel2);
    			userInput.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    			userInput.setTitle("User Input");
    			userInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    			userInput.setVisible(true);
    			outputStream.writeUTF(messageOut);
    		} 
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		panel.add(accept);
    		panel.add(reject);
    		acceptReject.add(panel);
    		acceptReject.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    		acceptReject.setTitle("Contoller authorizing user job and vehicles");
    		acceptReject.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		acceptReject.setVisible(true);
    		
    		ActionListener acceptListen = new acceptListener();
    		accept.addActionListener(acceptListen);
    		ActionListener rejectListen = new acceptListener();
    		accept.addActionListener(rejectListen);
    	}
    }
	
    class acceptListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		messageOut = "Accept";
    	}
    }
    
    class rejectListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent event) {
    		messageOut = "Reject";
    	}
    }
    
}
