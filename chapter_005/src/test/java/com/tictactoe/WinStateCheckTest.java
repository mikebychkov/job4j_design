package com.tictactoe;

import org.junit.Test;
import static org.junit.Assert.*;

public class WinStateCheckTest {

    @Test
    public void whenWinRow() {
        String[][] ar = {
                {"", "", ""},
                {"X", "X", "X"},
                {"", "", ""},
        };
        WinState ws = new WinStateCheck();
        assertTrue(ws.isWin(ar, "X"));
    }

    @Test
    public void whenWinColumn() {
        String[][] ar = {
                {"", "X", ""},
                {"", "X", ""},
                {"", "X", ""},
        };
        WinState ws = new WinStateCheck();
        assertTrue(ws.isWin(ar, "X"));
    }

    @Test
    public void whenWinFirstDiagonal() {
        String[][] ar = {
                {"X", "", ""},
                {"", "X", ""},
                {"", "", "X"},
        };
        WinState ws = new WinStateCheck();
        assertTrue(ws.isWin(ar, "X"));
    }

    @Test
    public void whenWinSecondDiagonal() {
        String[][] ar = {
                {"", "", "X"},
                {"", "X", ""},
                {"X", "", ""},
        };
        WinState ws = new WinStateCheck();
        assertTrue(ws.isWin(ar, "X"));
    }

    @Test
    public void whenNotWin() {
        String[][] ar = {
                {"X", "", "X"},
                {"", "X", ""},
                {"", "X", ""},
        };
        WinState ws = new WinStateCheck();
        assertFalse(ws.isWin(ar, "X"));
    }
}
