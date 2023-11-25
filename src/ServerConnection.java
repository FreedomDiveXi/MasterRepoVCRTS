import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
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

    /**
     * Since we're handling threads, this simply initializes a server
     * and begins to listen for requests when we run the server thread.
     * On initialization, it will it actively listens for a new connection
     * and accepts the new connection onto a separate thread, and begins
     * to listen to requests provided by the client handler.
     */
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

    /**
     * when the server accepts a client handler will be created
     * and will listen to requests provided by the respective client
     */
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
                trackClient(access);

                String previousRequest = "";
                while(true){
                    String inputLine = inputStream.readUTF();

                    System.out.println("==== Server received following request ===\n" + inputLine);
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
            } catch (IOException | SQLException e) {
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

        /**
         * Registers the proper client communication links so that we can send
         * and receive the proper messages to and from the client
         * @param access the id token the client sends when connected to the server
         */
        public void trackClient(String access){
            if(access.equals("job-user")){
                clientOutputStreams.put(access, outputStream);
                clientInputStreams.put(access,inputStream);
                System.out.println("connected job user");
            }
            if(access.equals("vehicle-user")){
                clientOutputStreams.put(access, outputStream);
                clientInputStreams.put(access,inputStream);
                System.out.println("connected vehicle user");
            }
            if(access.equals("controller")){
                clientOutputStreams.put(access,outputStream);
                clientInputStreams.put(access,inputStream);
            }

        }

        /**
         * This is our server request handler. Based on the request iw will send the appropriate
         * message back to the respective user
         * @param request request received by the user
         * @throws IOException
         */

        public void dispatcher(String request) throws IOException, SQLException {
            if(request.equals("accept")){
                if(objectData[0].equals("user-request-job")){
                    System.out.println("=== Job has been approved. Creating job... ===");
                    acceptIncomingJob(objectData);
                    clientOutputStreams.get("job-user").writeUTF("accepted-job");
                }

                if(objectData[0].equals("user-request-vehicle")){
                    System.out.println("=== Vehicle has been approved. Creating vehicle... ===");
                    acceptIncomingVehicle(objectData);
                    clientOutputStreams.get("vehicle-user").writeUTF("accepted-vehicle");
                }
            }
            if(request.equals("reject")){
                if(objectData[0].equals("user-request-job")){
                    System.out.println("=== Controller has denied job creation. ===\n");
                    clientOutputStreams.get("job-user").writeUTF("rejected-job");
                }

                if(objectData[0].equals("user-request-vehicle")){
                    System.out.println("=== Controller has denied vehicle creation. ===\n");
                    clientOutputStreams.get("vehicle-user").writeUTF("rejected-vehicle");
                }
            }

            if (request.equals("user-request-ju")) {
                controller.createJobOwner(objectData[1], objectData[2]);
                System.out.println("=== Server has accepted job user ===\n");
                outputStream.writeUTF("user-accept");
            }
            // vehicle user
            if (request.equals("user-request-vu")) {
                controller.createVehicleOwner(objectData[1], objectData[2]);
                System.out.println("=== Server has accepted vehicle user ===\n");
                outputStream.writeUTF("user-accept");
            }

            if (request.equals("user-request-job")){
                System.out.println("=== Server has received vehicle. Waiting for approval... ===\n");
                if(objectData.length == 4)
                    clientOutputStreams.get("controller").writeUTF("request-confirmation::" + objectData[1] + "::" + objectData[2] + "::" + objectData[3]);
                if(objectData.length == 5)
                    clientOutputStreams.get("controller").writeUTF("request-confirmation::" + objectData[1] + "::" + objectData[2] + "::" + objectData[3] + "::" + objectData[4]);
            }
            if(request.equals("user-request-vehicle")){
                System.out.println("=== Server has received vehicle. Waiting for approval... ===\n");
                clientOutputStreams.get("controller").writeUTF("request-confirmation::" + objectData[1] +"::"+objectData[2] +"::"+objectData[3]+"::"+objectData[4] + "::" + objectData[5]);

            }

            if(request.equals("process-jobs")){
                String temp = controller.startProcessing();
                DataOutputStream controllerOutput = clientOutputStreams.get("controller");
                System.out.println("=====\nPROCESSING JOBS \n=====");
                controllerOutput.writeUTF("html::"+temp);
            }

        }
        public void acceptIncomingJob(String[] request) throws SQLException {
			// deadline check
			if(request.length == 4)
				controller.createJob(request[1],request[2],request[3]);
			if(request.length == 5)
				controller.createJob(request[1],request[2],request[3],request[4]);

			System.out.println(controller.acceptJob());
        }

        public void acceptIncomingVehicle(String[] request) throws SQLException {
			controller.createVehicle(request[1],request[2],request[3],request[4],request[5]);
			System.out.println(controller.acceptVehicle());
        }
    }
}
