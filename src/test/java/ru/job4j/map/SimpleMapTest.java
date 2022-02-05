package ru.job4j.map;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleMapTest {

    SimpleMap<Integer, String> map = new SimpleMap<>();

    @Before
    public void initData() {
        map.put(1, "kitten");
        map.put(3, "doggy");
    }

    @Test
    public void whenAddNew() {
        assertEquals("kitten", map.get(1));
        assertEquals("doggy", map.get(3));
    }

    @Test
    public void whenAddExistingKey() {
        assertFalse(map.put(3, "little duck"));
    }

    @Test
    public void whenRemoveKey() {
        map.remove(3);
        assertNull(map.get(3));
    }

    @Test
    public void whenRemoveAbsentKey() {
        assertFalse(map.remove(4));
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertNull(iterator.next());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        Map<Integer, String> map1 = new SimpleMap<>();
        map1.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(8, "Armadillo");
        iterator.next();
    }

    @Test
    public void whenExpand() {
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(4, "F");
        map.put(5, "G");
        map.put(6, "H");
        map.put(7, "I");
        map.put(8, "J");
        map.put(9, "K");
        map.put(10, "L");
        map.put(11, "M");
        map.put(12, "N");
        Assert.assertEquals("M", map.get(11));
        Assert.assertEquals("N", map.get(12));
    }

}