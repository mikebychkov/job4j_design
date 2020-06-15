package com.tictactoe;

public class NextMoveGen implements NextMove {

    private String[][] field;
    private String marker;
    private int rows;
    private int columns;

    @Override
    public Cell getNextCellToMove(String[][] field, String marker) {
        if (field.length == 0) {
            return new Cell(-1, -1);
        }
        this.marker = marker;
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;

        return new Cell(0, 0);
    }
}
