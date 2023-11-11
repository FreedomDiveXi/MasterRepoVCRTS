import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConnection{
    private static final int PORT = 9806;
    private ExecutorService executorService; // Thread pool for managing clients

    public ServerConnection() {
        executorService = Executors.newCachedThreadPool();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (!serverSocket.isClosed()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    executorService.submit(clientHandler); // Handle the client in a new thread
                } catch (IOException e) {
                    System.out.println("I/O error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port " + PORT);
            e.printStackTrace();
        }
    }
        class ClientHandler implements Runnable {
            private Socket clientSocket;
            private DataInputStream inputStream;
            private DataOutputStream outputStream;

            public ClientHandler(Socket socket) {
                this.clientSocket = socket;
            }

            @Override
            public void run() {
                try {
                    inputStream = new DataInputStream(clientSocket.getInputStream());
                    outputStream = new DataOutputStream(clientSocket.getOutputStream());

                    String inputLine;
                    while ((inputLine = inputStream.readUTF()) != null) {
                        // Echo the message back to the client
                        outputStream.writeUTF("Server received: " + inputLine);
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client " + clientSocket.getInetAddress().getHostAddress());
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                        if (outputStream != null) outputStream.close();
                        if (clientSocket != null) clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    //    public void startServer() {
//        String messageIn = "";
//        System.out.println("server is running");
//        try {
//            serverSocket = new ServerSocket(9806);
//            socket = serverSocket.accept();
//            inputStream = new DataInputStream(socket.getInputStream());
//            outputStream = new DataOutputStream(socket.getOutputStream());
//
//            // will read the things coming from client.
//            while (!messageIn.equals("done")) {
//                messageIn = inputStream.readUTF();
//                String[] parts = messageIn.split("::");
//
//                // job user
//                if (parts[0].equals("user-request-ju")) {
//                    run.createJobOwner(parts[1], parts[2]);
//                    outputStream.writeUTF("user-accept");
//                }
//
//                // vehicle user
//                if (parts[0].equals("user-request-vu")) {
//                    run.createVehicleOwner(parts[1], parts[2]);
//                    outputStream.writeUTF("user-accept");
//                }
////				if (parts[0].equals("user-request-job") || parts[0].equals("user-request-vehicle")) {
////					System.out.println("got a new request for an item");
////					int result = JOptionPane.showOptionDialog(
////							null, // Use the main frame as the parent
////							"Would you like to accept the incoming " + (parts[0].equals("user-request-job") ? "job?" : "vehicle?"),
////							"INCOMING REQUEST!",
////							JOptionPane.YES_NO_OPTION,
////							JOptionPane.INFORMATION_MESSAGE,
////							null,
////							new String[]{"Accept", "Reject"}, // Provide explicit options
////							"Accept" // Default option
////					);
////					if (parts[0].equals("user-request-job")) {
////						handleJobRequest(result, parts);
////					} else {
////						handleVehicleRequest(result, parts);
////					}
////				}
//            }
//        } catch (Exception e) {
//            System.out.println("Failure from server");
//            e.printStackTrace();
//        }
//    }
    public void handleJobRequest(int result,String[] request) {
//		if(result == JOptionPane.YES_OPTION){
//			// deadline check
//			if(request.length == 4)
//				run.createJob(request[1],request[2],request[3]);
//			if(request.length == 5)
//				run.createJob(request[1],request[2],request[3],request[4]);
//
//			System.out.println(run.acceptJob());
//
//			try {
//				outputStream.writeUTF("job-accept");
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		if(result == JOptionPane.NO_OPTION){
//			// deadline check
//			if(request.length == 4)
//				run.createJob(request[1],request[2],request[3]);
//			if(request.length == 5)
//				run.createJob(request[1],request[2],request[3],request[4]);
//
//			System.out.println(run.rejectJob());
//		}
    }

    public void handleVehicleRequest(int result, String[] request) {
//		if(result == JOptionPane.YES_OPTION){
//			run.createVehicle(request[1],request[2],request[3],request[4],request[5]);
//			System.out.println(run.acceptVehicle());
//			try {
//				outputStream.writeUTF("vehicle-accept");
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		}

//		if(result == JOptionPane.NO_OPTION){
//			run.createVehicle(request[1],request[2],request[3],request[4],request[5]);
//			System.out.println(run.rejectVehicle());
//		}
    }
}
