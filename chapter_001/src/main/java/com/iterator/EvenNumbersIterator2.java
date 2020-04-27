package com.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator2 implements Iterator<Integer> {
    private final int[] arr;
    private int pointer = -1;

    public EvenNumbersIterator2(int[] param) {
        this.arr = param;
    }

    @Override
    public boolean hasNext() {
        for (int i = this.pointer + 1; i < this.arr.length; i++) {
            if (this.arr[i] % 2 == 0) {
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
        for (int i = this.pointer + 1; i < this.arr.length; i++) {
            if (this.arr[i] % 2 == 0) {
                this.pointer = i;
                return this.arr[i];
            }
        }
        return null;
    }
}

