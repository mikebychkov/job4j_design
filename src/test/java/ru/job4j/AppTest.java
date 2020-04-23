package ru.job4j;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class AppTest {

    @Test
    public void testApp1() {
        assertThat(false, is(false));
    }

    @Test
    public void testApp2() {
        assertThat(true, is(true));
    }
}
