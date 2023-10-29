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

	public String getVehicleOwnerDetails(){

		StringBuilder str = new StringBuilder();
		str.append("Vehicle Owner Username: ")
				.append(getUsername() +"\n")
				.append("---Owned Vehicles---\n")
				.append(listOwnedVehicles()).append("======\n");

		return String.valueOf(str);
	}

	private String listOwnedVehicles(){
		StringBuilder str = new StringBuilder();

		for (Vehicle currentVehilce: getUserVehicleList()){
			str.append("Vehicle Id: "+currentVehilce.getVehicleId() +'\n')
					.append("Vehicle Model: "+currentVehilce.getModel() +"\n");
		}

		return String.valueOf(str);
	}

}
