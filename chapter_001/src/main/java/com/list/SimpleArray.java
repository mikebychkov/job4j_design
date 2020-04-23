package com.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] data;
    private int defGrowth = 10;
    private int pointer = -1;
    private int modCount = 0;

    public SimpleArray() {
        initDataArray(defGrowth);
    }

    public SimpleArray(int initLen) {
        initDataArray(initLen);
    }

    private void initDataArray(int initLen) {
        this.data = new Object[initLen];
    }

    public T get(int index) {
        if (index > this.pointer) {
            throw new NoSuchElementException();
        }
        return (T) this.data[index];
    }

    public void add(T model) {
        this.pointer++;
        checkIfDataLengthIsEnough();
        this.data[this.pointer] = model;
        modCount++;
    }

    private void checkIfDataLengthIsEnough() {
        if (this.pointer == this.data.length) {
            this.data = Arrays.copyOf(this.data, this.data.length + this.defGrowth);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator(this);
    }

    private class SimpleIterator implements Iterator<T> {

        private SimpleArray<T> obj;
        private int modCount = 0;
        private int pointer = 0;

        public SimpleIterator(SimpleArray<T> obj) {
            this.obj = obj;
            this.modCount = obj.modCount;
        }

        @Override
        public boolean hasNext() {
            return this.pointer <= obj.pointer;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.modCount != obj.modCount) {
                throw new ConcurrentModificationException();
            }
            return (T) obj.data[this.pointer++];
        }
    }
}
