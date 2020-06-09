package com.solid.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParkingTest {

    Parking park;

    @Before
    public void init() {
        park = new CarParking(10, 2, 3);
    }

    @Test
    public void submitCar() {
        Vehicle car = new Car("AB 303");
        park.submitCar(car);
        List<Vehicle> exp = List.of(car);
        assertThat(park.getCarList(), is(exp));
    }

    @Test
    public void submitTruck() {
        Vehicle truck = new Truck("GG 447");
        park.submitCar(truck);
        List<Vehicle> exp = List.of(truck);
        assertThat(park.getTruckList(), is(exp));
    }

    @Test
    public void dismissCar() {
        Vehicle car = new Car("AB 303");
        park.submitCar(car);
        assertThat(park.getCarList().size(), is(1));
        park.dismissCar(car);
        assertThat(park.getCarList().size(), is(0));
    }

    @Test
    public void dismissTruck() {
        Vehicle truck = new Truck("GG 447");
        park.submitTruck(truck);
        assertThat(park.getTruckList().size(), is(1));
        park.dismissTruck(truck);
        assertThat(park.getTruckList().size(), is(0));
    }

    @Test
    public void getCarFreePlaces() {
        Vehicle car1 = new Car("AB 303");
        Vehicle car2 = new Car("AB 304");
        Vehicle truck = new Truck("GG 447");
        park.submitCar(car1);
        park.submitCar(car2);
        park.submitCar(truck);
        assertThat(park.getCarFreePlaces(), is(5));
    }

    @Test
    public void getTruckFreePlaces() {
        Vehicle truck = new Truck("GG 447");
        park.submitTruck(truck);
        assertThat(park.getTruckFreePlaces(), is(1));
    }

    @Test
    public void getTruckExtraPlaces() {
        Vehicle truck = new Truck("GG 447");
        park.submitCar(truck);
        assertThat(park.getTruckExtraPlaces(), is(2));
    }
}
