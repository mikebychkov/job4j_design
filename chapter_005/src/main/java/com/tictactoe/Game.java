package com.tictactoe;

public class Game {
    private String[][] field;
    private NextMove nextMove;
    private FieldPainter fieldPainter;
    private WinState winState;
    private String userMarker = "X";
    private String compMarker = "O";
    private int rows;
    private int columns;

    public Game(FieldPainter fp, NextMove nm, WinState ws) {
        this(fp, nm, ws, 3, 3);
    }

    public Game(FieldPainter fp, NextMove nm, WinState ws, int rows, int columns) {
        this.fieldPainter = fp;
        this.nextMove = nm;
        this.winState = ws;
        this.rows = rows;
        this.columns = columns;
        this.field = new String[rows][columns];
        initField();
    }

    public void initField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field[i][j] = "";
            }
        }
    }

    public void paint() {
        fieldPainter.paint(field.clone());
    }

    public boolean makeUserMove(Cell cell) {
        return makeMove(cell, userMarker);
    }

    public boolean makeCompMove() {
        Cell cell = nextMove.getNextCellToMove(field.clone(), compMarker, userMarker);
        return makeMove(cell, compMarker);
    }

    private boolean makeMove(Cell cell, String marker) {
        if (cell.row >= rows || cell.column >= columns
            || cell.row < 0 || cell.column < 0) {
            return false;
        }
        if (!field[cell.row][cell.column].isEmpty()) {
            return false;
        }
        this.field[cell.row][cell.column] = marker;
        return true;
    }

    public boolean isUserWin() {
        return isWin(userMarker);
    }

    public boolean isCompWin() {
        return isWin(compMarker);
    }

    private boolean isWin(String marker) {
        return winState.isWin(field.clone(), marker);
    }

    public boolean gameFieldIsFull() {
        FieldAnalyser fa = new FieldAnalyser(field.clone());
        return fa.gameFieldIsFull();
    }
}
