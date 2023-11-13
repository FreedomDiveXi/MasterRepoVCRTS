import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private final String serverAddress;
    private final int serverPort;

    public ClientConnection(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connectToServer() throws IOException {
        // Establish a connection with the server
        socket = new Socket(serverAddress, serverPort);

        // Create input and output streams for communication
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void sendMessage(String message) throws IOException {
        // Send a message to the server
        outputStream.writeUTF(message);
    }

    public String receiveMessage() throws IOException {
        // Read a message from the server
        return inputStream.readUTF();
    }

    // closes connection
    public void closeConnection() throws IOException {
        // Close the connection
        if (socket != null) socket.close();
        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
    }
}
