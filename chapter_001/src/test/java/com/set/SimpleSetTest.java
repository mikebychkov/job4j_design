package com.set;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleSetTest {

    @Test
    public void addThenIT() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(5);
        assertThat(set.iterator().next(), is(5));
    }

    @Test(expected = NoSuchElementException.class)
    public void noAddThenIT() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }
}
