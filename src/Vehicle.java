import java.util.ArrayList;

public class Vehicle {
    private int vehicleId;
    private String vehicleOwner;
    private String make;
    private String model;
    private int year;

    private static ArrayList<Integer> currentVehicleIds = new ArrayList<>();

    public Vehicle(String vehicleOwner, int vehicleId, String make, String model, int year) {
        if (isUniqueId(vehicleId)) {
            this.vehicleId = vehicleId;
            currentVehicleIds.add(vehicleId);
        } else {
            throw new IllegalArgumentException("Duplicate vehicleId, please input a number other than " + vehicleId);
        }
        this.vehicleOwner = vehicleOwner;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getVehicleOwner(){
        return vehicleOwner;
    }

	public int getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
    
    private static boolean isUniqueId(int vehicleId) {
        return !currentVehicleIds.contains(vehicleId);
        // true - doesn't exist yet, false, exists
    }  
}
