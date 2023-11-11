import javax.swing.*;
import java.io.*;

public class GUIMain {
	public static void main (String [] args) throws IOException {

		server server = new server();
		server.start();

		client something = new client();
		something.start();

	}
}
