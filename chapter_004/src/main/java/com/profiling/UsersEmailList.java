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
            String in_comm = input.nextLine();
            if (in_comm.equals("add")) {
                System.out.println("Enter user name:");
                String in_user = input.nextLine();
                System.out.println("Enter user emails (comma separated):");
                String in_emails = input.nextLine();
                dataMap.put(in_user, in_emails);
            } else if (in_comm.equals("process")) {
                Map<String, String> rslMap = UsersEmailList.correctEmailList(dataMap);
                System.out.println(rslMap);
            } else if (in_comm.equals("clear")) {
                dataMap.clear();
            } else if (in_comm.equals("exit")) {
                exit = true;
            }
        }
    }
}
