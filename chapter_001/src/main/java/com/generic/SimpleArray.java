package com.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int pointer = 0;

    public SimpleArray(int len) {
        this.objects = new Object[len];
    }

    public int size() {
        return this.pointer;
    }

    public void add(T model) {
        Objects.checkIndex(this.pointer, this.objects.length);
        this.objects[this.pointer++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, this.size());
        this.objects[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, this.size());
        int len = this.size() - 1 - index;
        System.arraycopy(this.objects, index + 1, this.objects, index, len);
        this.objects[this.size() - 1] = null;
        this.pointer--;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.size());
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
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) sa.objects[itPointer++];
        }
    }
}
