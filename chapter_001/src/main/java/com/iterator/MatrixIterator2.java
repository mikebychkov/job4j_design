package com.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator2 implements Iterator<Integer> {
    private final int[][] data;
    private int pointer1;
    private int pointer2;

    public MatrixIterator2(int[][] matrix) {
        this.data = matrix;
    }

    @Override
    public boolean hasNext() {
        return this.pointer1 < this.data.length
                && this.pointer2 < this.data[this.pointer1].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements to provide");
        }
        Integer rsl = this.data[this.pointer1][this.pointer2++];
        if (this.pointer2 == this.data[this.pointer1].length) {
            this.pointer1++;
            this.pointer2 = 0;
        }
        return rsl;
    }
}
