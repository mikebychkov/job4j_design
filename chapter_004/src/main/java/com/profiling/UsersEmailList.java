package com.profiling;

import java.util.*;

public class UsersEmailList {

    public static Map<String, String> correctEmailList(Map<String, String> elist) {

        Map<String, String> sMap = new TreeMap<>(elist);
        Map<String, String> emap = new HashMap<>();
        Map<String, String> rsl = new HashMap<>();

        for (Map.Entry<String, String> ent : sMap.entrySet()) {
            String usr = ent.getKey();
            String[] emails = ent.getValue().split(",");
            for (String email : emails) {
                if (emap.containsKey(email)) {
                    usr = emap.get(email);
                    break;
                }
            }
            StringJoiner sj = new StringJoiner(",");
            String userEmails = rsl.getOrDefault(usr, "");
            if (!userEmails.isEmpty()) {
                sj.add(userEmails);
            }
            for (String email : emails) {
                emap.put(email, usr);
                if (!userEmails.contains(email)) {
                    sj.add(email);
                }
            }
            rsl.put(usr, sj.toString());
        }
        return rsl;
    }

    public static void main(String[] args) {
        boolean exit = false;
        Map<String, String> dataMap = new HashMap<>();
        Scanner input = new Scanner(System.in);
        while (!exit) {
            System.out.println("Enter command (add, process, clear, exit):");
            String inComm = input.nextLine();
            if (inComm.equals("add")) {
                System.out.println("Enter user name:");
                String inUser = input.nextLine();
                System.out.println("Enter user emails (comma separated):");
                String inEmails = input.nextLine();
                dataMap.put(inUser, inEmails);
            } else if (inComm.equals("process")) {
                Map<String, String> rslMap = UsersEmailList.correctEmailList(dataMap);
                System.out.println(rslMap);
            } else if (inComm.equals("clear")) {
                dataMap.clear();
            } else if (inComm.equals("exit")) {
                exit = true;
            }
        }
    }
}
