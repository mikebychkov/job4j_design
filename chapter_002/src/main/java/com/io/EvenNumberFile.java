package com.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            for (String str : sb.toString().split(System.lineSeparator())) {
                int num = Integer.valueOf(str);
                System.out.println(num + " is " + (num % 2 == 0 ? "even" : "odd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
