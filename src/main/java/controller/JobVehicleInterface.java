package controller;

import users.vehicleOwner.VehicleOwner;

import java.util.ArrayList;

public interface JobVehicleInterface<T> {


    //method that will create a user
    public VehicleOwner createUser(String id, String password);

    public ArrayList<T> addToGlobalList (T item);

    public void updateStatus();

    public ArrayList<T> removeItemFromGlobalList(T item);

    public ArrayList<T> updateGlobalList(ArrayList<T> globalList);

    //method that will be able to add something their respected entity onto their list
    public ArrayList<T> addToList(T item, ArrayList <T> list );

    //method that will remove item from the list
    public ArrayList<T> removeFromList(T item, ArrayList <T> list );

    // method that will print the details from the list,
    // Meant to be served as a way to print out to a file
    public void  printDetails(ArrayList<T> list);

}
