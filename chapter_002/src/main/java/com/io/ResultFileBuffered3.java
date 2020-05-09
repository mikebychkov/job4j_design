package com.io;

import java.io.*;

public class ResultFileBuffered3 {

    public static void main(String[] args) {
        try (BufferedWriter out = new BufferedWriter(
                        new FileWriter("result.txt")
                )) {
            out.write("Hello, world! " + System.lineSeparator() + "(version 3)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
