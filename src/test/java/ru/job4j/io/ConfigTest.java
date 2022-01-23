package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Anastasia Ostroumova"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Anastasia"));
        assertThat(config.value("surname"), is("Ostroumova"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairErrors() {
        String path = "./data/pair_with_errors.properties";
        Config config = new Config(path);
        config.load();
    }
}