package com.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String> log = getFileStrings(source);
        List<String> rsl = new ArrayList<>();
        String newRecord = "";
        for (String str : log) {
            String[] strData = str.split(" ");
            if (strData[0].equals("400") || strData[0].equals("500")) {
                if (newRecord.isEmpty()) {
                    newRecord += strData[1] + ";";
                }
                continue;
            }
            if (newRecord.isEmpty()) {
                continue;
            }
            if (strData[0].equals("200") || strData[0].equals("300")) {
                newRecord += strData[1] + ";";
                rsl.add(newRecord);
                newRecord = "";
            }
        }
        writeFileStrings(target, rsl);
    }

    public List<String> getFileStrings(String filename) {
        List<String> out = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(filename))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public void writeFileStrings(String filename, List<String> data) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filename))) {
            for (String str : data) {
                out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./chapter_002/unavailable.csv";
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            out.println("15:01:30;15:02:32;");
            out.println("15:10:30;23:12:32;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
