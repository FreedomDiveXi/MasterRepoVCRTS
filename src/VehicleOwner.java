import java.util.ArrayList;

public class VehicleOwner extends User{

	ArrayList <Vehicle> userVehicleList;
	public VehicleOwner(String username, String password) {
		super(username, password);
		userVehicleList = new ArrayList<>();
	}

	public ArrayList<Vehicle> getUserVehicleList(){
		return userVehicleList;
	}

	public void addVehicleToVehicleUserList(Vehicle vehicle){
		userVehicleList.add(vehicle);
	}
	
}
