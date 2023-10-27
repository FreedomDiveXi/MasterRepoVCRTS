import javax.swing.*;
import java.io.*;

public class GUIMain {
	public static void main (String [] args) throws IOException {
		JFrame startPage = new StartPage();
		startPage.setTitle("Welcome to the Vehicular Cloud");
		startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPage.setVisible(true);

		// object creation sample controllers.
//		CloudController controller = new CloudController();
//
//		VehicleOwner vehicleOwnerTest = controller.createVehicleOwner("VHO@gmail.com", "toyotaO123wner");
//		Vehicle car1 = controller.createVehicle("Jack", "6676767", "camry", "toyota", "2001");
//		Vehicle car2 = controller.createVehicle("kennedy", "13131313", "gle", "mercedes", "2015");
//		Vehicle car3 = controller.createVehicle("Mack", "525252", "G63", "BMW", "2020");
//		Vehicle car4 = controller.createVehicle("Daniel", "212121212", "civic", "honda", "2017");
//		Vehicle car5 = controller.createVehicle("Carl", "45454545", "odessey", "honda", "2005");
//		Vehicle car6 = controller.createVehicle("Kristi", "87878787", "MDX", "Acura", "2008");

//		// job owner has access to job.
//		System.out.println("\nTesting job owner has connection to job");
//		jobOwnerTest.listAllJobs();

		// global user list.
//		UserList globalUsers = controller.getUsers();

//		System.out.println(vehicleOwnerTest);
//
//		System.out.println("\n===Testing global user list===");
//		globalUsers.listAllUsers();
//
//		System.out.println("\n===Testing vehicle creation");
//		Vehicle car1 = controller.createVehicle("Jack", "2312232", "camry", "toyota", "2001");
//		Vehicle car2 = controller.createVehicle("kennedy", "677878", "gle", "mercedes", "2015");

//		System.out.println(car1);
//
//		System.out.println("\n===Testing controller available vehicle list");
//		System.out.println(controller.getAvailableVehicles() + " = available vehicles");
//		System.out.println(controller.getAllVehicles() + " all vehicle list");
//		System.out.println(vehicleOwnerTest.getUserVehicleList() + " personal owner vehicle list");

		// Testing the vehicle job processing.
//		JobOwner jobOwnerTest = controller.createJobOwner("hello@gmail.com", "240kjh121", "Group3", "718-232-2332");
//		Job jobTest0 = controller.createJob("daniel", "123", "5");
//		Job jobTest1 = controller.createJob("jack", "456", "10");
//		Job jobTest2 = controller.createJob("Jerry", "789", "23");
//		Job jobTest3 = controller.createJob("Mack", "3433447", "3");
//		Job jobTest4 = controller.createJob("Taylor", "834", "4");

//		System.out.println(jobTest0);
//		System.out.println(jobTest1);
//		System.out.println(jobTest2);
//		System.out.println(jobTest0.getAssignedVehicles());

		// controller process.
//		System.out.println(controller.startProcessing());


	}
}