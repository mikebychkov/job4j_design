package com.tictactoe;

public interface NextMove {

    Cell getNextCellToMove(String[][] field, String marker, String enemyMarker);
}
