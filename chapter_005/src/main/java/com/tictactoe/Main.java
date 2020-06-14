package com.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static boolean compGoesFirst() {
        Random rnd = new Random();
        return rnd.nextInt(2) == 0;
    }

    public static void init(Game game) {
        game.initField();
        if (compGoesFirst()) {
            System.out.println("===Computer goes first===");
            game.makeCompMove();
        } else {
            System.out.println("===User goes first===");
        }
        game.paint();
    }

    public static void printScores(int userScore, int compScore) {
        System.out.printf("Score:%nPlayer: %s%nComputer: %s%n", userScore, compScore);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FieldPainter fp = new ConsolePainter();
        NextMove nm = new NextMoveGen();
        Game game = new Game(fp, nm);

        init(game);

        int userScore = 0;
        int compScore = 0;

        boolean exitMode = false;
        while (!exitMode) {
            System.out.println("Enter coordinates to mark (use comma sign as a separator):");
            String input = sc.nextLine();
            if ("exit".equals(input)) {
                exitMode = true;
                continue;
            }
            String[] inputData = input.split(",");
            if (inputData.length < 2) {
                System.out.println("There is two coordinates needed!");
                continue;
            }
            int row = 0;
            int column = 0;
            try {
                row = Integer.parseInt(inputData[0]);
                column = Integer.parseInt(inputData[1]);
            } catch (NumberFormatException e) {
                System.out.println("You need to specify coordinates in numbers!");
                continue;
            }
            Boolean moveRsl = game.makeUserMove(new Cell(row, column));
            if (!moveRsl) {
                System.out.println("Sorry, you can't use this coordinates!");
                continue;
            }
            game.paint();
            if (game.isUserWin()) {
                userScore++;
                System.out.println("Player WIN!");
                printScores(userScore, compScore);
                init(game);
                continue;
            }
            game.makeCompMove();
            game.paint();
            if (game.isCompWin()) {
                compScore++;
                System.out.println("Computer WIN!");
                printScores(userScore, compScore);
                init(game);
            }
        }
    }
}
