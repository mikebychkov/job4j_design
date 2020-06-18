package com.tictactoe;

public interface GameFactory {

    FieldPainter getFieldPainter();
    NextMove getNextMove();
    WinState getWinState();
}
