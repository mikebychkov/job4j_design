package com.io;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        //String path = "app1.properties";
        String path = "../app0.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        assertThat(
                config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCommentedKey() {
        String path = "app1.properties";
        Config config = new Config(path);
        config.load();
        config.value("qwas");
    }
}
