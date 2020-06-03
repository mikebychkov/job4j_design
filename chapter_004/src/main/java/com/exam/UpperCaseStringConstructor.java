package com.exam;

import java.util.List;
import java.util.StringJoiner;

public class UpperCaseStringConstructor {

    public static String make1(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str.toUpperCase()).append(",");
        }
        return sb.toString();
    }

    public static String make1PostUp(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append(",");
        }
        return sb.toString().toUpperCase();
    }

    public static String make2(List<String> list) {
        StringJoiner sb = new StringJoiner(",");
        list.stream().map(str -> str.toUpperCase()).forEach(sb::add);
        return sb.toString();
    }

    public static String make2PostUp(List<String> list) {
        StringJoiner sb = new StringJoiner(",");
        list.stream().forEach(sb::add);
        return sb.toString().toUpperCase();
    }

    public  static String make3(List<String> list) {
        String rsl = "";
        for (String str : list) {
            rsl += str.toUpperCase() + ",";
        }
        return rsl;
    }

    public  static String make3PostUp(List<String> list) {
        String rsl = "";
        for (String str : list) {
            rsl += str + ",";
        }
        return rsl.toUpperCase();
    }
}
