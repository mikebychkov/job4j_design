package com.tdd.tdd;

public class Ticket3D implements Ticket {
    private int id;

    public Ticket3D(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ticket3D && this.id == ((Ticket3D) obj).id;
    }
}
