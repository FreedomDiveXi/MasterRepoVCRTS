package controller;

import users.vehicleOwner.Vehicle;
import users.vehicleOwner.VehicleOwner;

import java.util.ArrayList;

/**
 * Our vehicle objects for now live as a singular entities.
 * The controller is meant to bridge them together.
 * That way we can separate the data as much as possible from function/behavior things (except for essential things).
 * So that way we don't have to directly work with the direct object.
 */

//todo
public class VehicleOwnerController implements JobVehicleInterface<Vehicle>{

    private ArrayList<Vehicle> globalVehicleList;
    private ArrayList <VehicleOwner> globalUserList;

    // singleton pattern to ensure there is only one instance of the vehicle controller.
    private VehicleOwnerController (){}
    private static VehicleOwnerController instance;
    public static VehicleOwnerController getInstance(){
        if(instance == null)
                instance = new VehicleOwnerController();
        return instance;
    }

    @Override
    public VehicleOwner createUser(String id, String password) {
        VehicleOwner newUser = new VehicleOwner(id, password);
        addToGlobalList(newUser);
        return newUser;
    }

    public Vehicle createNewVehicle(VehicleOwner user,String model, String make, int year){
        Vehicle newVehicle = new Vehicle(model,make,year);
        addToGlobalList(newVehicle);
        user.getVechicleList().add(newVehicle);
        return newVehicle;
    }
    public void addToGlobalList(VehicleOwner item){
        globalUserList.add(item);
    }
    public void addToGlobalList(Vehicle item){
        globalVehicleList.add(item);
    }

    @Override
    public void updateStatus(Vehicle vehicleInUse) {
        if(!vehicleInUse.isInUse())
            vehicleInUse.setInUse(true);
        vehicleInUse.setInUse((false));
    }

}