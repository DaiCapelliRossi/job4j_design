package ru.job4j.linkedlist;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private int modCount;
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;


    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> f = first;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            SimpleLinkedList.Node<E> node = first;

            @Override
            public boolean hasNext() {
                return (node != null);
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E e = node.item;
                node = node.next;
                return e;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
