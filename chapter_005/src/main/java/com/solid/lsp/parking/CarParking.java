package com.solid.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private List<Vehicle> carList = new ArrayList<>();
    private List<Vehicle> truckList = new ArrayList<>();
    private final int carCont;
    private final int truckCount;
    private final int truckSize;
    private int carIndex = 0;
    private int truckIndex = 0;

    public CarParking(int carCont, int truckCount, int truckSize) {
        this.carCont = carCont;
        this.truckCount = truckCount;
        this.truckSize = truckSize;
    }

    @Override
    public boolean submitCar(Vehicle car) {
        return false;
    }

    @Override
    public boolean submitTruck(Vehicle truck) {
        return false;
    }

    @Override
    public boolean dismissCar(Vehicle car) {
        return false;
    }

    @Override
    public boolean dismissTruck(Vehicle truck) {
        return false;
    }

    @Override
    public int getCarFreePlaces() {
        return 0;
    }

    @Override
    public int getTruckFreePlaces() {
        return 0;
    }

    @Override
    public int getTruckExtraPlaces() {
        return 0;
    }

    @Override
    public List<Vehicle> getCarList() {
        return null;
    }

    @Override
    public List<Vehicle> getTruckList() {
        return null;
    }
}
