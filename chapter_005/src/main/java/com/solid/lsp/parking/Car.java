package com.solid.lsp.parking;

public class Car implements Vehicle {
    private String number;

    public Car(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public boolean isTruck() {
        return false;
    }
}
