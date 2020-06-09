package com.solid.lsp.parking;

import java.util.List;

public interface Parking {

    boolean submitCar(Vehicle car);
    boolean submitTruck(Vehicle truck);
    boolean dismissCar(Vehicle car);
    boolean dismissTruck(Vehicle truck);
    int getCarFreePlaces();
    int getTruckFreePlaces();
    int getTruckExtraPlaces();
    List<Vehicle> getCarList();
    List<Vehicle> getTruckList();
}
