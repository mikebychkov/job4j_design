package com.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class SimpleArrayTest {

    @Test
    public void testAddAndGet() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        assertThat(arr.get(1), is("qwas2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNonExisted() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        arr.get(4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addThenRemoveThenGet() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        arr.remove(0);
        arr.get(2);
    }

    @Test
    public void testSet() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        arr.add("qwas4");
        arr.set(0, "QWAS11");
        arr.set(3, "QWAS13");
        assertThat(arr.get(0), is("QWAS11"));
        assertThat(arr.get(3), is("QWAS13"));
    }

    @Test
    public void testRemove() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        arr.remove(0);
        arr.remove(0);
        assertThat(arr.get(0), is("qwas3"));
    }

    @Test
    public void testSize() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        assertThat(arr.size(), is(3));
        arr.add("qwas4");
        arr.add("qwas5");
        assertThat(arr.size(), is(5));
        arr.remove(0);
        assertThat(arr.size(), is(4));
    }

    @Test
    public void testIterator() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("qwas1");
        arr.add("qwas2");
        arr.add("qwas3");
        Iterator<String> it = arr.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next(), is("qwas2"));
    }
}
