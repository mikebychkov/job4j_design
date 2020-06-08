package com.solid.lsp.parking;

public interface Parking {

    boolean submitCar(Vehicle car);
    boolean submitTruck(Vehicle truck);
    boolean dismissCar(Vehicle car);
    boolean dismissTruck(Vehicle truck);
    int getCarFreePlaces();
    int getTruckFreePlaces();
}
