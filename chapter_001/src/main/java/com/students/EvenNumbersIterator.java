package com.students;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] arr;
    private int pointer = 0;

    public EvenNumbersIterator(int[] param) {
        int[] rsl = new int[param.length];
        int index = 0;
        for (int i : param) {
            if (i % 2 == 0) {
                rsl[index++] = i;
            }
        }
        this.arr = Arrays.copyOf(rsl, index);
    }

    @Override
    public boolean hasNext() {
        return this.pointer < this.arr.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements to provide");
        }
        return this.arr[this.pointer++];
    }
}
