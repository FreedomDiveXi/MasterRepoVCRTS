import javax.swing.*;
import java.io.*;

public class GUIMain {
	public static void main (String [] args) throws IOException {
		JFrame startPage = new StartPage();
		startPage.setTitle("Welcome to the Vehicular Cloud");
		startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPage.setVisible(true);
	}
}
