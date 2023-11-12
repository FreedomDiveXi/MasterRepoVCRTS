import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConnection {
    private static final int PORT = 9806;
    private ExecutorService executorService; // Thread pool for managing clients
    private CloudController controller;
    private Map<String, DataOutputStream> clientOutputStreams = new ConcurrentHashMap<>();
    private Map<String, DataInputStream> clientInputStreams = new ConcurrentHashMap<>();
    String[] objectData;

    public ServerConnection() {
        executorService = Executors.newCachedThreadPool();
        controller = new CloudController();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (!serverSocket.isClosed()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(clientSocket, controller);
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
        private CloudController controller;

        public ClientHandler(Socket socket, CloudController controller) {
            this.clientSocket = socket;
            this.controller = controller;
        }

        @Override
        public void run() {
            try {
                inputStream = new DataInputStream(clientSocket.getInputStream());
                outputStream = new DataOutputStream(clientSocket.getOutputStream());

                String access = inputStream.readUTF();

                if(access.equals("client")){
                    clientOutputStreams.put(access, outputStream);
                    clientInputStreams.put(access,inputStream);
                }
                if(access.equals("controller")){
                    clientOutputStreams.put(access,outputStream);
                    clientInputStreams.put(access,inputStream);
                }

                String previousRequest = "";
                while(true){
                    String inputLine = inputStream.readUTF();

                    System.out.println("Retrieved Information: " + inputLine);
                    if(inputLine.contains("::")){
                        objectData= inputLine.split("::");
                        inputLine = objectData[0];
                    }
                    if(!previousRequest.equals(inputLine)){
                        dispatcher(inputLine);
                        previousRequest = inputLine;
                    }else{
                        dispatcher(inputLine);
                    }
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

        public void dispatcher(String request) throws IOException {
            if (request.equals("user-request-ju")) {
                controller.createJobOwner(objectData[1], objectData[2]);
                outputStream.writeUTF("user-accept");
            }
            // vehicle user
            if (request.equals("user-request-vu")) {
                controller.createVehicleOwner(objectData[1], objectData[2]);
                outputStream.writeUTF("user-accept");
            }

            if (request.equals("user-request-job") || request.equals("user-request-vehicle")) {
                clientOutputStreams.get("controller").writeUTF("request-confirmation");
            }

            if(request.equals("accept")){
                System.out.println("thing has been accepted");
                if(objectData[0].equals("user-request-job")){
                    acceptIncomingJob(objectData);
                    clientOutputStreams.get("client").writeUTF("accepted-job");
                }

                if(objectData[0].equals("user-request-vehicle")){
                    acceptIncomingVehicle(objectData);
                    clientOutputStreams.get("client").writeUTF("accepted-vehicle");
                }
            }
            if(request.equals("reject")){
                System.out.println("Object creation has been denied.");
                //todo getting a weird error actually calling methods though the print statement works the same. no objects are created/registered
//                if(objectData[0].equals("user-request-job"))
//                    controller.rejectJob();
//                if(objectData[0].equals("user-request-vehicle"))
//                    controller.rejectVehicle();
            }

            if(request.equals("process-jobs")){
                String temp = controller.startProcessing();
                DataOutputStream controllerOutput = clientOutputStreams.get("controller");
                controllerOutput.writeUTF(temp);
            }

        }
        public void acceptIncomingJob(String[] request) {
			// deadline check
			if(request.length == 4)
				controller.createJob(request[1],request[2],request[3]);
			if(request.length == 5)
				controller.createJob(request[1],request[2],request[3],request[4]);

			System.out.println(controller.acceptJob());

        }

        public void acceptIncomingVehicle(String[] request) {
			controller.createVehicle(request[1],request[2],request[3],request[4],request[5]);
			System.out.println(controller.acceptVehicle());
        }
    }
}
