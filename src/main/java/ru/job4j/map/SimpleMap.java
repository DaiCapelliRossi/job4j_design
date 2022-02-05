package ru.job4j.map;

import java.lang.reflect.Array;
import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int hc = key.hashCode();
        int hash = hash(hc);
        int ind = indexFor(hash);

        if (table[ind] != null) {
            return false;
        }

        table[ind] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        int h;
        //CHECKSTYLE:OFF
        return ((hashCode == 0) ? 0 : (h = hashCode) ^ (h >>> 16));
        //CHECKSTYLE:ON
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            MapEntry<K, V>[] tempTable = table;
            table = new MapEntry[capacity * 2];
            for (MapEntry<K, V> entry : tempTable) {
                if (entry != null) {
                    table[indexFor(hash(entry.key.hashCode()))] = entry;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int hc = key.hashCode();
        int hash = hash(hc);
        int ind = indexFor(hash);

        if (table[ind] == null) {
            return null;
        }

        if (table[ind].key.hashCode() == hc && key.equals(table[ind].key)) {
            return table[ind].value;
        }

        return null;
    }

    @Override
    public boolean remove(K key) {

        int hc = key.hashCode();
        int hash = hash(hc);
        int ind = indexFor(hash);

        if ((table[ind] == null)) {
            return false;
        }

        K tableKey = table[ind].key;
        if (hc == tableKey.hashCode() && key.equals(tableKey)) {
            table[ind] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                for (int i = point; i < table.length - 1; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                MapEntry<K, V> entry = table[point];
                if (entry == null) {
                    point++;
                    return null;
                }
                K k = entry.key;
                point++;
                return k;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args) {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "F");
        map.put(4, "G");
        map.put(5, "H");
        map.put(6, "I");
        map.put(7, "J");
        map.put(8, "K");
        map.put(9, "L");
        map.put(10, "M");
        map.put(11, "N");
        map.put(12, "N");
        for (Integer entry : map) {
            System.out.println(entry);
        }
    }

}