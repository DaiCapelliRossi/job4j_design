package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfLessThanThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeIf(input, x -> x < 3);
        assertThat(input, is(Arrays.asList(3, 4, 5)));
    }

    @Test
    public void whenRemoveIfMoreThanThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeIf(input, x -> x > 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenReplaceIfContainsTru() {
        List<String> input = new ArrayList<>(Arrays.asList("Trust", "Truth", "Kindness"));
        ListUtils.replaceIf(input, x -> x.contains("Tru"), "It's all about trust");
        assertThat(input, is(Arrays.asList("It's all about trust", "It's all about trust", "Kindness")));
    }

    @Test
    public void whenReplaceIfMoreThanThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, x -> x > 3, 100);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 100, 100)));
    }

    @Test
    public void whenRemoveAllNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 5));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(2, 3, 4)));
    }

    @Test
    public void whenRemoveAllWords() {
        List<String> input = new ArrayList<>(Arrays.asList("Trust", "Truth", "Kindness"));
        List<String> elements = new ArrayList<>(Arrays.asList("Kindness"));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList("Trust", "Truth")));
    }

    @Test
    public void whenRemoveAll() {
        List<String> input = new ArrayList<>(Arrays.asList("Trust", "Truth", "Kindness", "Truth"));
        List<String> elements = new ArrayList<>(Arrays.asList("Truth"));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList("Trust", "Kindness")));
    }

}