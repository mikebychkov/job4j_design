package com.tdd.kissdryyagny;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search2 {

    public static List<File> findBy(Predicate<File> pr) {
        List<File> rsl = new ArrayList<>();
        List<File> files = null;
        for (File file : files) {
            if (pr.test(file)) {
                rsl.add(file);
            }
        }
        return rsl;
    }

    public static List<File> findByMask(String mask) {
        return findBy(file -> Pattern.matches(mask, file.getName()));
    }

    public static List<File> findByName(String name) {
        return findBy(file -> name.equals(file.getName()));
    }

    public static List<File> findByExt(String ext) {
        return findBy(file -> file.getName().endsWith(ext));
    }
}
