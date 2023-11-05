import javax.swing.*;
import java.io.*;

public class GUIMain {
	public static void main (String [] args) throws IOException {
		 JFrame startPage = new StartPage();
		 startPage.setTitle("Welcome to the Vehicular Cloud");
		 startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 startPage.setVisible(true);
		 
		 JFrame controllerPage = new ControllerPage();
		 controllerPage.setTitle("Welcome to the Controller view");
		 controllerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 controllerPage.setVisible(true);
	}
}
