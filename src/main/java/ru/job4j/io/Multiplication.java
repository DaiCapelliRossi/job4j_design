package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiplication {
    public static void main(String[] args) {
        Multiplication multiplication = new Multiplication();
        multiplication.multiply(10, "multiplication_table.txt");

    }

    public int[][] multiply(int size, String fileName) {
        int[][] table = new int[size][size];
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            for (int outer = 0; outer < size; outer++) {
                for (int inner = 0; inner < size; inner++) {
                    out.write(((outer + 1) * (inner + 1) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}
