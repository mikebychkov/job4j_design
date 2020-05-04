package com.exam;

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
            String userEmails = rsl.get(usr);
            if (userEmails != null) {
                sj.add(userEmails);
            }
            for (String email : emails) {
                emap.put(email, usr);
                if (userEmails != null && userEmails.contains(email)) {
                    continue;
                }
                sj.add(email);
            }
            rsl.put(usr, sj.toString());
        }
        return rsl;
    }
}
