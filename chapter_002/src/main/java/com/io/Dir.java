package com.io;

import java.io.File;

public class Dir {

    public static long getFileSize(File fl) {
        if (!fl.isDirectory()) {
            return fl.length();
        }
        long rsl = 0;
        for (File subfl : fl.listFiles()) {
            if (subfl.isDirectory()) {
                rsl += getFileSize(subfl);
            } else {
                rsl += subfl.length();
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        File file = new File("c:/projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(
                String.format("Name: %s, size: %s", file.getAbsolutePath(), getFileSize(file))
        );
        for (File subfile : file.listFiles()) {
            System.out.println(
                    String.format("Name: %s, size: %s", subfile.getAbsolutePath(), getFileSize(subfile))
            );
        }
    }
}
