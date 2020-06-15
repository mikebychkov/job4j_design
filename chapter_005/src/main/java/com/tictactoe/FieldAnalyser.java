package com.tictactoe;

public class FieldAnalyser {

    private String[][] field;
    private String marker;
    private int rows;
    private int columns;

    public FieldAnalyser(String[][] field) {
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;
    }

    public boolean checkAll(String marker, int rowIndex, int columnIndex,
                                        int rowIncrement, int columnIncrement) {
        while (rowIndex < this.rows && rowIndex >= 0
                && columnIndex < this.columns && columnIndex >= 0) {
            if (!marker.equals(this.field[rowIndex][columnIndex])) {
                return false;
            }
            rowIndex += rowIncrement;
            columnIndex += columnIncrement;
        }
        return true;
    }

    public int checkAny(String marker, int rowIndex, int columnIndex,
                            int rowIncrement, int columnIncrement) {
        int rsl = 0;
        while (rowIndex < this.rows && rowIndex >= 0
                && columnIndex < this.columns && columnIndex >= 0) {
            if (marker.equals(this.field[rowIndex][columnIndex])) {
                rsl++;
            }
            rowIndex += rowIncrement;
            columnIndex += columnIncrement;
        }
        return rsl;
    }

    public Cell getNextEmpty(int rowIndex, int columnIndex,
                        int rowIncrement, int columnIncrement) {
        while (rowIndex < this.rows && rowIndex >= 0
                && columnIndex < this.columns && columnIndex >= 0) {
            if (this.field[rowIndex][columnIndex].isEmpty()) {
                return new Cell(rowIndex, columnIndex);
            }
            rowIndex += rowIncrement;
            columnIndex += columnIncrement;
        }
        return new Cell(-1, -1);
    }

    public boolean gameFieldIsFull() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.field[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
