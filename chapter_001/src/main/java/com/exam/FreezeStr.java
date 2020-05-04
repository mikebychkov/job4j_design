package com.exam;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {

    public static Map<Character, Integer> charMap(String word) {
        Map<Character, Integer> leters = new HashMap<>();
        for (char ch : word.toCharArray()) {
            leters.put(ch, leters.getOrDefault(ch, 0) + 1);
        }
        return leters;
    }

    public static boolean eq(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        Map<Character, Integer> letersLeft = charMap(left);
        Map<Character, Integer> letersRight = charMap(right);
        for (Map.Entry<Character, Integer> ent : letersLeft.entrySet()) {
            if (letersRight.get(ent.getKey()) != ent.getValue()) {
                return false;
            }
        }
        return true;
    }
}
