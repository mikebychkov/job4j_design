package com.solid.lsp.parking;

public class Truck implements Vehicle {
    private String number;

    public Truck(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public boolean isTruck() {
        return true;
    }
}
