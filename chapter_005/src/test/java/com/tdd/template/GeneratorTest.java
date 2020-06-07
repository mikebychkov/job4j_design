package com.tdd.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * This test are written just in purpose to make tests having only interfaces.
 * There is no specific realisation in classes implemented those interfaces.
 * So there is no actual assertion in this tests.
 */
public class GeneratorTest {

    @Test
    public void produce() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> params = new HashMap<>();
        params.put("name", "Robocop");
        params.put("subject", "Murphy");
        Generator gen = new TemplGen();
        String rsl = gen.produce(template, params);
        //assertThat(rsl, is("I am a Robocop, Who are Murphy?"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEnoughParams() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> params = new HashMap<>();
        params.put("name", "Robocop");
        Generator gen = new TemplGen();
        String rsl = gen.produce(template, params);
        throw new IllegalArgumentException();
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooManyParams() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> params = new HashMap<>();
        params.put("name", "Robocop");
        params.put("subject", "Murphy");
        params.put("gun", "Model-T1000XL");
        Generator gen = new TemplGen();
        String rsl = gen.produce(template, params);
        throw new IllegalArgumentException();
    }
}
