package com.list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleListTest {

    @Test
    public void whenAddThenGet() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        String rsl = list.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        String rsl = list.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmpty() {
        SimpleList<String> list = new SimpleList<>();
        list.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetOutBound() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleList<String> list = new SimpleList<>();
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        Iterator<String> it = list.iterator();
        list.add("second");
        it.next();
    }

    @Test
    public void whenAddThenIT2Times() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.add("second");
        Iterator<String> it = list.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }

    @Test
    public void whenAddThenIT2DifferentTimes() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.add("second");
        assertThat(list.iterator().next(), is("first"));
        assertThat(list.iterator().next(), is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt2() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
    }

    @Test
    public void whenAddThenGet2DifferentTimes() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.add("second");
        assertThat(list.get(0), is("first"));
        assertThat(list.get(1), is("second"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetNonExisted() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.get(1);
    }
}
