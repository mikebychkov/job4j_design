package com.tictactoe;

public class WinStateCheck implements WinState {

    private String[][] field;
    private int rows;
    private int columns;
    private String marker;

    @Override
    public boolean isWin(String[][] field, String marker) {
        if (field.length == 0) {
            return false;
        }
        this.marker = marker;
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;

        boolean rsl = checkInRows();
        if (!rsl) {
            rsl = checkInColumns();
        }
        if (!rsl) {
            rsl = checkInDiagonals();
        }

        return rsl;
    }

    private boolean checkInRows() {
        for (int i = 0; i < this.rows; i++) {
            boolean rsl = check(i, 0, 0, 1);
            if (rsl) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInColumns() {
        for (int i = 0; i < this.columns; i++) {
            boolean rsl = check(0, i, 1, 0);
            if (rsl) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInDiagonals() {
        return check(0, 0, 1, 1)
                || check(this.rows - 1, 0, -1, 1);
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
