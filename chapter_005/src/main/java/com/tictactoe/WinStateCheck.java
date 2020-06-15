package com.tictactoe;

public class WinStateCheck implements WinState {

    private String[][] field;
    private int rows;
    private int columns;
    private String marker;
    private FieldAnalyser fa;

    @Override
    public boolean isWin(String[][] field, String marker) {
        if (field.length == 0) {
            return false;
        }
        this.marker = marker;
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;

        this.fa = new FieldAnalyser(field);

        boolean rsl = checkRows();
        if (!rsl) {
            rsl = checkColumns();
        }
        if (!rsl) {
            rsl = checkDiagonals();
        }

        return rsl;
    }

    private boolean checkRows() {
        for (int i = 0; i < rows; i++) {
            boolean rsl = fa.checkAll(marker, i, 0, 0, 1);
            if (rsl) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < columns; i++) {
            boolean rsl = fa.checkAll(marker, 0, i, 1, 0);
            if (rsl) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return fa.checkAll(marker, 0, 0, 1, 1)
                || fa.checkAll(marker, rows - 1, 0, -1, 1);
    }
}
