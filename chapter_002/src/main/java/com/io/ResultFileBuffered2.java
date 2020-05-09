package com.io;

import java.io.*;

public class ResultFileBuffered2 {

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("result.txt")
                ))) {
            out.write("Hello, world! " + System.lineSeparator() + "(version 2)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
