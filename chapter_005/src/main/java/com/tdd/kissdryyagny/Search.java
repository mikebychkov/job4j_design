package com.tdd.kissdryyagny;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Search {
    static List<File> findByMask(String mask) {
        List<File> rsl = new ArrayList<>();
        List<File> files = null;
        for (File file : files) {
            if (Pattern.matches(mask, file.getName())) {
                rsl.add(file);
            }
        }
        return rsl;
    }

    static List<File> findByName(String name) {
        List<File> rsl = new ArrayList<>();
        List<File> files = null;
        for (File file : files) {
            if (name.equals(file.getName())) {
                rsl.add(file);
            }
        }
        return rsl;
    }

    static List<File> findByExt(String ext) {
        List<File> rsl = new ArrayList<>();
        List<File> files = null;
        for (File file : files) {
            if (file.getName().endsWith(ext)) {
                rsl.add(file);
            }
        }
        return rsl;
    }
}
