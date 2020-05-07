package com.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static org.junit.Assert.assertThat;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> out = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String str : out) {
            if (str.isEmpty()) {
                continue;
            }
            if (str.startsWith("##")) {
                continue;
            }
            if (!str.contains("=")) {
                continue;
            }
            String[] strData = str.split("=");
            this.values.put(strData[0], strData[1]);
        }
    }

    public String value(String key) {
        if (!this.values.containsKey(key)) {
            throw new NoSuchElementException();
        }
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./chapter_002/app1.properties"));
        System.out.println(new Config("app0.properties"));
    }
}
