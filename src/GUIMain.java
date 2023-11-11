import javax.swing.*;
import java.io.*;

public class GUIMain {
	public static void main (String [] args) throws IOException {

		new Thread(()->{
			new ServerConnection().start();
		}).start();

		new Thread(()->{
			SwingUtilities.invokeLater(()->{
				try {
					new ControllerPage();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			});
		}).start();

		new Thread(()->{
			SwingUtilities.invokeLater(()->{
				try {
					new StartPage();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			});
		}).start();

	}
}
