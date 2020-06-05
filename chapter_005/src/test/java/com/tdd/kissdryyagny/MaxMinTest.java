package com.tdd.kissdryyagny;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxMinTest {

    @Test
    public void testMaxMin1() {
        MaxMin mm = new MaxMin();
        List<String> list = List.of("str", "str123", "strsuper");
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int i1 = s1.contains("super") ? 10 : 0;
                int i2 = s2.contains("super") ? 10 : 0;
                return i1 - i2;
            }
        };
        String rslMax = mm.max(list, comp);
        String rslMin = mm.min(list, comp);
        assertThat(rslMax, is(list.get(2)));
        assertThat(rslMin, is(list.get(0)));
    }

    @Test
    public void testMin() {
        MaxMin mm = new MaxMin();
        List<String> list = List.of("str", "str123", "strsuper");
        String rslMax = mm.max(list, (s1, s2) -> s1.length() - s2.length());
        String rslMin = mm.min(list, (s1, s2) -> s1.length() - s2.length());
        assertThat(rslMax, is(list.get(2)));
        assertThat(rslMin, is(list.get(0)));
    }
}
