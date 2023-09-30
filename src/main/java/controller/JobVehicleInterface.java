package controller;

import java.util.ArrayList;

public interface JobVehicleInterface<T> {
    //method that will be able to add something their respected entity onto their list
    public ArrayList<T> addToList(T Item, ArrayList <T> list );

    //method that will remove item from the list
    public ArrayList<T> removeFromList(T Item, ArrayList <T> list );

    // method that will print the details from the list,
    // Meant to be served as a way to print out to a file
    public void  printDetails(ArrayList<T> list);

}
