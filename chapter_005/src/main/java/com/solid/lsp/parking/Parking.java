package com.solid.lsp.parking;

public interface Parking {

    boolean submitCar(Car car);
    boolean submitTruck(Truck truck);
    boolean dismissCar(Car car);
    boolean dismissTruck(Truck truck);
    int getCarFreePlaces();
    int getTruckFreePlaces(int truckSize);
}
