import javax.swing.*;
import java.io.*;
import java.sql.SQLException;

public class GUIMain {
	public static void main (String [] args) throws IOException, InterruptedException, SQLException {

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

		// used for testing purposes
//		CloudController something = new CloudController();
//		something.createJobOwner("something","something");
//		something.createJob("something","123", "123");
//		// the idea is that on acceptance the system will upload the job to the database aswell
//		something.acceptJob();

	}
}
