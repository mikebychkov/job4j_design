package com.students;

import java.util.Iterator;

public class MatrixIterator implements Iterator {
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
    public Object next() {
        return this.flatMatrix[this.pointer++];
    }
}
