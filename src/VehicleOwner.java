import java.util.ArrayList;

public class VehicleOwner extends User{

	ArrayList <Vehicle> vehicleList;
	public VehicleOwner(String username, String password) {
		super(username, password);
		vehicleList = new ArrayList<>();
	}

	// generic getter
	public ArrayList<Vehicle> getVehicleList(){
		return vehicleList;
	}

	// controller uses this
	public void addVehicleToVehicleUserList(Vehicle vehicle){
		vehicleList.add(vehicle);
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

		for (Vehicle currentVehilce: getVehicleList()){
			str.append("Vehicle Id: "+currentVehilce.getVehicleId() +'\n')
					.append("Vehicle Model: "+currentVehilce.getModel() +"\n");
		}

		return String.valueOf(str);
	}

}
