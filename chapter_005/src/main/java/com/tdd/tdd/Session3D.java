package com.tdd.tdd;

public class Session3D implements Session {

    private int id;

    public Session3D(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Session3D && this.id == ((Session3D) obj).id;
    }
}
