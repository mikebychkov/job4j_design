package com.refs;

import com.gc.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cache {

    private Map<String, SoftReference<String>> data = new HashMap<>();

    public Cache(Path directory) throws IOException {
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException();
        }
        List<Path> files = Files.list(directory).filter(p -> p.toFile().getName().endsWith(".txt")).collect(Collectors.toList());
        for (Path file : files) {
            this.data.put(file.toFile().getName(), new SoftReference<>(getFileText(file)));
        }
    }

    private String getFileText(Path file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
            br.lines().forEach(sb::append);
        }
        return sb.toString();
    }

    public String getFileCache(String fileName) {
        return this.data.get(fileName).get();
    }

    public static void main(String[] args) throws IOException {
        Cache cc = new Cache(Paths.get("./txt"));
        String cache1 = cc.getFileCache("names.txt");
        System.out.println(cache1);
        String cache2 = cc.getFileCache("addresses.txt");
        System.out.println(cache2);
    }
}
