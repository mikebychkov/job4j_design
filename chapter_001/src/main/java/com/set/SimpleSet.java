package com.set;

import com.list.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> data = new SimpleArray<>();

    public void add(T t) {
        if (isPresent(t)) {
            return;
        }
        this.data.add(t);
    }

    private boolean isPresent(T t) {
        for (T it : data) {
            if (it.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.data.iterator();
    }
}
