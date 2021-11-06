package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count;

    public T poll() {
        if (count == 0) {
            while (true) {
                try {
                    out.push(in.pop());
                    count++;
                } catch (NoSuchElementException e) {
                    break;
                }
            }
        }
        count--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}