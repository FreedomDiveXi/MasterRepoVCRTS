package controller;

import users.vehicleOwner.Vehicle;
import users.vehicleOwner.VehicleOwner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class VehicleOwnerController {
    private static VehicleOwnerController instance;
    private final String VEHICLE_USER_DATABASE = "VehicleOwnerDataBase.txt";
    private final String VEHICLE_DATABASE = "VehicleDataBase.txt";
    private final ArrayList<Vehicle> globalVehicleList;
    private final ArrayList<VehicleOwner> globalVehicleUserList;
    private final FileWriter writeUser = new FileWriter(VEHICLE_USER_DATABASE, true);
    private final PrintWriter printUser = new PrintWriter(writeUser, true);
    private final FileWriter writeVehicle = new FileWriter(VEHICLE_DATABASE, true);
    private final PrintWriter printVehicle = new PrintWriter(writeVehicle, true);


    // singleton pattern to ensure there is only one instance of the vehicle controller.
    private VehicleOwnerController() throws IOException {
        globalVehicleList = new ArrayList<Vehicle>();
        globalVehicleUserList = new ArrayList<VehicleOwner>();
    }

    public static VehicleOwnerController getInstance() throws IOException {
        if (instance == null)
            instance = new VehicleOwnerController();
        return instance;
    }

    /**
     * will create a new user, and will append it to the global user list and
     * will write to user database.
     */
    public VehicleOwner createUser(String id, String password) throws IOException {
        VehicleOwner newUser = new VehicleOwner(id, password);
        try {
            writeToVehicleUserFile(addToGlobalList(newUser));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newUser;
    }

    /**
     * will create a new vehicle, and will append it to the global vehicle list.
     */
    public Vehicle createNewVehicle(VehicleOwner user, String model, String make, int year) throws IOException {
        Vehicle newVehicle = new Vehicle(model, make, year);
        user.getVehicleList().add(newVehicle);

        try {
            writeToVehicleFile(addToGlobalList(newVehicle));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newVehicle;
    }

    public void writeToVehicleUserFile(ArrayList<VehicleOwner> vehicleOwners) throws IOException {
        for (VehicleOwner currentUser : vehicleOwners) {
            writeUser.write(currentUser.getUserDetails());
            printUser.println();
        }
        // no point in closing since the user can add a new vehicle and want
        // to update the users info with the most up-to-date values
    }

    public void writeToVehicleFile(ArrayList<Vehicle> vehicles) throws IOException {
        for (Vehicle currentVehicle : vehicles) {
            writeVehicle.write(currentVehicle.getVehicleDetails());
            printVehicle.println();
        }
        // no point in closing since the user can add a new vehicle and want
        // to update the users info with the most up-to-date values
        // since we've registered a new care for some users, we have to also go ahead and update the vehicleUserFile
        updateVehicleUserFile(getGlobalVehicleUserList());
    }

    public void updateVehicleUserFile(ArrayList<VehicleOwner> list) throws IOException {
        for (VehicleOwner currentUser : list) {
            writeUser.write(currentUser.getUserDetails());
            printUser.println();
        }
    }

    public ArrayList<VehicleOwner> addToGlobalList(VehicleOwner user) {
        globalVehicleUserList.add(user);
        return globalVehicleUserList;
    }

    public ArrayList<Vehicle> addToGlobalList(Vehicle vehicle) {
        globalVehicleList.add(vehicle);
        return globalVehicleList;
    }

    public ArrayList<Vehicle> getGlobalVehicleList() {
        return globalVehicleList;
    }

    public ArrayList<VehicleOwner> getGlobalVehicleUserList() {
        return globalVehicleUserList;
    }

    // when the prof gives us the algo that adds assigns jobs to vehicle
    // will use this to update the status of a vehicle.
    public void updateStatus(Vehicle vehicleInUse) {
        if (!vehicleInUse.isInUse())
            vehicleInUse.setInUse(true);
        vehicleInUse.setInUse((false));
    }

}
