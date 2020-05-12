package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UsageLog4j2 {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Anthony Hopkins";
        int age = 82;
        Calendar birthday = new GregorianCalendar(1937,11, 31);
        int oscarNominations = 5;
        int oscarCount = 1;
        String oscarMovie = "The Silence of the Lambs";
        String nationality = "British";
        boolean isChevalier = true;

        LOG.debug("User name : {}, age : {}, birthday : {}", name, age, birthday.getTime());
        LOG.debug("Oscar nominations : {}, vins : {} at the \"{}\" movie", oscarNominations, oscarCount, oscarMovie);
        LOG.debug("User is {} and his chevalier status is : {}", nationality, isChevalier);
    }
}
