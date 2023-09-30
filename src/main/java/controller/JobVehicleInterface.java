package controller;

import users.vehicleOwner.Vehicle;
import users.vehicleOwner.VehicleOwner;

import java.util.ArrayList;

public interface JobVehicleInterface<T> {

    public VehicleOwner createUser(String id, String password);

    public void updateStatus(T item);

}
