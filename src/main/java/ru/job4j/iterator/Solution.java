package ru.job4j.iterator;

public class Solution {
    public static Cat cat;

    public static class Cat {
        String name;
    }
    static {
        cat = new Cat();
        cat.name = "Una";
        System.out.println(cat.name);
    }

    public static void main(String[] args) {

    }
}


