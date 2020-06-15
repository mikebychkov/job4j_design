package com.tictactoe;

import java.util.StringJoiner;

public class ConsolePainter implements FieldPainter {

    @Override
    public void paint(String[][] field) {
        if (field.length == 0) {
            return;
        }
        int rows = field.length;
        int columns = field[0].length;

        // HEADER
        StringJoiner sj = new StringJoiner("");
        sj.add("\t");
        for (int j = 0; j < columns; j++) {
            sj.add(String.format("%s\t", j));
        }
        System.out.println(sj.toString());

        // ROWS
        StringJoiner sjCol = new StringJoiner("");
        for (int i = 0; i < rows; i++) {
            sjCol.add(String.format("%s\t", i));
            for (int j = 0; j < columns; j++) {
                String str = field[i][j];
                str = str.isEmpty() ? " " : str;
                sjCol.add(String.format("[%s]\t", str));
            }
            sjCol.add(System.lineSeparator());
        }
        System.out.println(sjCol.toString());
    }
}
