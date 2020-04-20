package com.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[] flatMatrix;
    private int pointer = 0;

    public MatrixIterator(int[][] matrix) {
        int len = 0;
        for (int[] x : matrix) {
            len += x.length;
        }
        this.flatMatrix = new int[len];
        int index = 0;
        for (int[] x : matrix) {
            for (int y : x) {
                this.flatMatrix[index++] = y;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.pointer < this.flatMatrix.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements to provide");
        }
        return this.flatMatrix[this.pointer++];
    }
}
