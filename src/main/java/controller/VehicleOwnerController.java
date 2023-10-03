package controller;

import users.vehicleOwner.Vehicle;
import users.vehicleOwner.VehicleOwner;

import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Our vehicle objects for now live as a singular entities.
 * The controller is meant to bridge them together.
 * That way we can separate the data as much as possible from function/behavior
 * things (except for essential things).
 * So that way we don't have to directly work with the direct object.
 */

//todo
public class VehicleOwnerController {

    private static VehicleOwnerController instance;
    private ArrayList<Vehicle> globalVehicleList;
    private ArrayList<VehicleOwner> globalUserList;
    FileWriter writeUser;
    {
        try {
            writeUser = new FileWriter("UserDataBase.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    PrintWriter printUser = new PrintWriter(writeUser);
    FileWriter writeVehicle;
    {
        try {
            writeVehicle = new FileWriter("VehicleDataBase.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    PrintWriter printVehicle = new PrintWriter(writeVehicle);

    // singleton pattern to ensure there is only one instance of the vehicle controller.
    private VehicleOwnerController() {
    }

    public static VehicleOwnerController getInstance() {
        if (instance == null)
            instance = new VehicleOwnerController();
        return instance;
    }

    /**
     * will create a new user, and will append it to the global user list.
     */

    public VehicleOwner createUser(String id, String password) {
        try {
            writeUser.write(id);
            printUser.print(" : ");
            writeUser.write(password);
            printUser.println();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"");
        }
        VehicleOwner newUser = new VehicleOwner(id, password);
        addToGlobalList(newUser);
        return newUser;
    }

    /**
     * will create a new vehicle, and will append it to the global vehicle list.
     */
    public Vehicle createNewVehicle(VehicleOwner user, String model, String make, int year) {
        Vehicle newVehicle = new Vehicle(model, make, year);
        addToGlobalList(newVehicle);
        user.getVechicleList().add(newVehicle);
        try {
            writeVehicle.write(user.getVehicleOwnerId());
            printVehicle.print(" : ");
            writeVehicle.write(model);
            printVehicle.print(" : ");
            writeVehicle.write(make);
            printVehicle.print(" : ");
            writeVehicle.write(year);
            printVehicle.print(" : ");
            writeVehicle.write(newVehicle.getDateTimeNow().toString());
            printVehicle.println();

            writeVehicle.close();
            printVehicle.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"");
        }
        return newVehicle;
    }

    public void addToGlobalList(VehicleOwner item) {
        globalUserList.add(item);
    }

    public void addToGlobalList(Vehicle item) {
        globalVehicleList.add(item);
    }

    public void updateStatus(Vehicle vehicleInUse) {
        if (!vehicleInUse.isInUse())
            vehicleInUse.setInUse(true);
        vehicleInUse.setInUse((false));
    }

}
