package com.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleMap2Test {

    private SimpleMap2<String, String> sm;

    @Before
    public void setup() {
        sm = new SimpleMap2<>();
    }

    @Test
    public void addThenGet() {
        sm.insert("qwas1", "record#1");
        assertThat(sm.get("qwas1"), is("record#1"));
    }

    @Test
    public void addSameKey() {
        sm.insert("qwas1", "record#1");
        assertThat(sm.insert("qwas1", "#1"), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void getNonExisted() {
        sm.insert("qwas1", "record#1");
        sm.get("qwas33");
    }

    @Test (expected = NoSuchElementException.class)
    public void addThenDeleteThenGet() {
        sm.insert("qwas1", "record#1");
        sm.delete("qwas1");
        sm.get("qwas1");
    }

    @Test
    public void addManyThenRehashThenGet() {
        sm.insert("qwas1", "record#1");
        sm.insert("qwas2", "record#2");
        sm.insert("qwas3", "record#3");
        sm.insert("qwas4", "record#4");
        sm.insert("qwas5", "record#5");
        sm.insert("qwas6", "record#6");
        sm.insert("qwas7", "record#7");
        sm.insert("qwas8", "record#8");
        sm.insert("qwas9", "record#9");
        sm.insert("qwas10", "record#10");
        sm.insert("qwas11", "record#11");
        sm.insert("qwas12", "record#12");
        sm.insert("qwas13", "record#13");
        sm.insert("qwas14", "record#14");
        sm.insert("qwas15", "record#15");
        sm.insert("qwas16", "record#16");
        assertThat(sm.get("qwas1"), is("record#1"));
    }

    @Test
    public void addThenIT() {
        sm.insert("qwas1", "record#1");
        Map.Entry<String, String> rsl = sm.iterator().next();
        assertThat(rsl.getValue(), is("record#1"));
    }
}
