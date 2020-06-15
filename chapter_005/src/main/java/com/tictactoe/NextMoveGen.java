package com.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class NextMoveGen implements NextMove {

    private String[][] field;
    private String marker;
    private String enemyMarker;
    private int rows;
    private int columns;
    private FieldAnalyser fa;
    private Map<Line, Integer> lines;

    @Override
    public Cell getNextCellToMove(String[][] field, String marker, String enemyMarker) {

        if (field.length == 0) {
            return new Cell(-1, -1);
        }
        this.marker = marker;
        this.enemyMarker = enemyMarker;
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;
        this.fa = new FieldAnalyser(field);
        this.lines = new HashMap<>();

        checkRows();
        checkColumns();
        checkDiagonals();

        Cell rsl;

        Line longestLine = getLongestLine();
        if (longestLine != null) {
            rsl = fa.getNextEmpty(longestLine.rowIndex, longestLine.columnIndex,
                    longestLine.rowIncrement, longestLine.columnIncrement);
        } else {
            rsl = getRandomEmptyCell();
        }

        return rsl;
    }

    private void checkRows() {
        for (int i = 0; i < rows; i++) {
            Line line = new Line(i, 0, 0, 1);
            combineTargetLine(line);
        }
    }
    private void checkColumns() {
        for (int i = 0; i < columns; i++) {
            Line line = new Line(0, i, 1, 0);
            combineTargetLine(line);
        }
    }
    private void checkDiagonals() {
        Line line1 = new Line(0, 0, 1, 1);
        combineTargetLine(line1);
        Line line2 = new Line(rows - 1, 0, -1, 1);
        combineTargetLine(line2);
    }

    private void combineTargetLine(Line line) {
        int rsl = fa.checkAny(marker, line.rowIndex, line.columnIndex, line.rowIncrement, line.columnIncrement);
        int rslE = fa.checkAny(enemyMarker, line.rowIndex, line.columnIndex, line.rowIncrement, line.columnIncrement);
        if (rslE == 0 && rsl > 0) {
            lines.put(line, rsl);
        }
    }

    private Line getLongestLine() {
        int maxLen = 0;
        Line longestLine = null;
        for (Map.Entry<Line, Integer> entry : lines.entrySet()) {
            int val = entry.getValue();
            if (val > maxLen) {
                maxLen = val;
                longestLine = entry.getKey();
            }
        }
        return longestLine;
    }

    private Cell getRandomEmptyCell() {
        Random rnd = new Random();
        Cell rsl = new Cell(rnd.nextInt(rows), rnd.nextInt(columns));
        if (!field[rsl.row][rsl.column].isEmpty()) {
            rsl = getRandomEmptyCell();
        }
        return rsl;
    }

    private class Line {
        int rowIndex;
        int columnIndex;
        int rowIncrement;
        int columnIncrement;

        public Line(int rowIndex, int columnIndex, int rowIncrement, int columnIncrement) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
            this.rowIncrement = rowIncrement;
            this.columnIncrement = columnIncrement;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Line line = (Line) o;
            return rowIndex == line.rowIndex
                    && columnIndex == line.columnIndex
                    && rowIncrement == line.rowIncrement
                    && columnIncrement == line.columnIncrement;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIndex, columnIndex, rowIncrement, columnIncrement);
        }
    }
}
