package com.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int pointer = 0;

    public SimpleArray(int len) {
        this.objects = new Object[len];
    }

    private boolean indexInvalid(int index) {
        if (index >= 0 && index < this.objects.length) {
            return false;
        }
        return true;
    }

    public boolean add(T model) {
        int index = this.pointer++;
        if (indexInvalid(index)) {
            return false;
        }
        this.objects[index] = model;
        return true;
    }

    public boolean set(int index, T model) {
        if (indexInvalid(index)) {
            return false;
        }
        this.objects[index] = model;
        return true;
    }

    public boolean remove(int index) {
        if (indexInvalid(index)) {
            return false;
        }
        for (int i = index; i < this.objects.length - 1; i++) {
            this.objects[i] = this.objects[i + 1];
        }
        this.objects[this.objects.length - 1] = null;
        return true;
    }

    public T get(int index) {
        if (indexInvalid(index)) {
            return null;
        }
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator(this);
    }

    private class SimpleIterator implements Iterator<T> {

        private final SimpleArray<T> sa;
        private int itPointer = 0;

        public SimpleIterator(SimpleArray<T> sa) {
            this.sa = sa;
        }

        @Override
        public boolean hasNext() {
            return this.itPointer < sa.objects.length;
        }

        @Override
        public T next() {
            return (T) sa.objects[itPointer++];
        }
    }
}
