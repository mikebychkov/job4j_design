package com.tdd.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D(2)));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D(1));
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D(1))));
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session ss1 = new Session3D(1);
        Session ss2 = new Session3D(2);
        Session ss3 = new Session3D(3);
        cinema.add(ss1);
        cinema.add(ss2);
        cinema.add(ss3);
        List<Session> sessions = cinema.find(session -> session.equals(ss2));
        assertThat(sessions, is(Arrays.asList(ss2)));
    }
}
