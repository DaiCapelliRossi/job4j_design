package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> elParent = findBy(parent);
        if (elParent.isPresent()) {
            Optional<Node<E>> elChild = findBy(child);
            if (elChild.isEmpty()) {
                elParent.get().children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                return Optional.of(el);
            };
            data.addAll(el.children);
        }
        return Optional.empty();
    }

    @Override
    public boolean isBinary() {
        return findByPredicate(parent -> parent.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(x -> x.value.equals(value));
    }
}