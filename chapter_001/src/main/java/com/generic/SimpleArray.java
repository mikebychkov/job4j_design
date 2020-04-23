package com.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int pointer = 0;

    public SimpleArray(int len) {
        this.objects = new Object[len];
    }

    public void add(T model) {
        this.objects[this.pointer++] = model;
    }

    public void set(int index, T model) {
        this.objects[index] = model;
    }

    public void remove(int index) {
        for (int i = index; i < this.objects.length - 1; i++) {
            this.objects[i] = this.objects[i + 1];
        }
        this.objects[this.objects.length - 1] = null;
    }

    public T get(int index) {
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
