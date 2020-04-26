package com.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void printMap() {
        User usr1 = new User("Nikolay",
                new GregorianCalendar(1990, 7, 5),
                2);
        User usr2 = new User("Nikolay",
                new GregorianCalendar(1990, 7, 5),
                2);

        Map<User, String> map = new HashMap<>();
        map.put(usr1, "record#1");
        map.put(usr2, "record#2");

        System.out.println(map);
    }
}
