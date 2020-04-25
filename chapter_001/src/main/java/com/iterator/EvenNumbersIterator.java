package com.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] arr;
    private int pointerNext = -1;

    public EvenNumbersIterator(int[] param) {
        this.arr = param;
        checkNext();
    }

    private void checkNext() {
        for (int i = this.pointerNext + 1; i < this.arr.length; i++) {
            if (this.arr[i] % 2 == 0) {
                this.pointerNext = i;
                return;
            }
        }
        this.pointerNext = -1;
    }

    @Override
    public boolean hasNext() {
        return this.pointerNext >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements to provide");
        }
        Integer rsl = this.arr[this.pointerNext];
        checkNext();
        return rsl;
    }
}
