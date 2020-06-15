package com.tictactoe;

public class FieldAnaliser {

    private String[][] field;
    private String marker;
    private int rows;
    private int columns;

    public FieldAnaliser(String[][] field, String marker) {
        this.marker = marker;
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;
    }

    private boolean check(int rowIndex, int columnIndex,
                          int rowIncrement, int columnIncrement) {
        for (int i = rowIndex; i < this.rows; i += rowIncrement) {
            for (int j = columnIndex; j < this.rows; j += columnIncrement) {
                if (!marker.equals(this.field[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
