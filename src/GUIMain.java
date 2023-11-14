import javax.swing.*;
import java.io.*;

public class GUIMain {
	public static void main (String [] args) throws IOException, InterruptedException {

		new Thread(()->{
			new ServerConnection().start();
		}).start();
		Thread.sleep(500);

		new Thread(()->{
			SwingUtilities.invokeLater(()->{
				try {
					new ControllerPage();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			});
		}).start();
		Thread.sleep(500);

		new Thread(()->{
			SwingUtilities.invokeLater(()->{
				try {
					new StartPage();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			});
		}).start();
		Thread.sleep(500);

	}
}
