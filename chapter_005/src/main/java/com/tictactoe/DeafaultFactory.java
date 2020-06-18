package com.tictactoe;

public class DeafaultFactory implements GameFactory {

    @Override
    public FieldPainter getFieldPainter() {
        return new ConsolePainter();
    }

    @Override
    public NextMove getNextMove() {
        return new NextMoveGen();
    }

    @Override
    public WinState getWinState() {
        return new WinStateCheck();
    }
}
