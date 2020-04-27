package com.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator2 implements Iterator<Integer> {
    private final int[] arr;
    private int pointer = 0;

    public EvenNumbersIterator2(int[] param) {
        this.arr = param;
    }

    @Override
    public boolean hasNext() {
        for (int i = this.pointer; i < this.arr.length; i++) {
            if (this.arr[i] % 2 == 0) {
                this.pointer = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements to provide");
        }
        return this.arr[this.pointer++];
    }
}

