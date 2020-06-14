package com.tictactoe;

public class Game {
    private String[][] field;
    private NextMove nextMove;
    private FieldPainter fieldPainter;
    private String userMarker = "X";
    private String compMarker = "O";
    private int rows;
    private int columns;

    public Game(FieldPainter fp, NextMove nm) {
        this(fp, nm, 3, 3);
    }

    public Game(FieldPainter fp, NextMove nm, int rows, int columns) {
        this.fieldPainter = fp;
        this.nextMove = nm;
        this.rows = rows;
        this.columns = columns;
        this.field = new String[rows][columns];
        initField();
    }

    public void initField() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.field[i][j] = "";
            }
        }
    }

    public void paint() {
        this.fieldPainter.paint(this.field.clone());
    }

    public boolean makeUserMove(Cell cell) {
        return makeMove(cell, this.userMarker);
    }

    public boolean makeCompMove() {
        Cell cell = this.nextMove.getNextCellToMove(this.field.clone());
        return makeMove(cell, this.compMarker);
    }

    private boolean makeMove(Cell cell, String marker) {
        if (cell.row >= this.rows || cell.column >= this.columns) {
            return false;
        }
        if (!this.field[cell.row][cell.column].isEmpty()) {
            return false;
        }
        this.field[cell.row][cell.column] = marker;
        return true;
    }

    public boolean isUserWin() {
        return isWin(this.userMarker);
    }

    public boolean isCompWin() {
        return isWin(this.compMarker);
    }

    private boolean isWin(String marker) {
        // TODO: 15.06.2020 Win condition
        return false;
    }
}
