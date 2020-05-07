package com.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void test1() {
        String path1 = "server.log";
        String path2 = "unavailable.csv";

        Analizy an = new Analizy();
        an.unavailable(path1, path2);

        assertThat(an.getFileStrings(path2).get(0), is("10:57:01;10:59:01;"));
    }
}
