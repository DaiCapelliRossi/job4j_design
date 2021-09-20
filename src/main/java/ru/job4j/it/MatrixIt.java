package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length - 1 && data[row].length == 0) {
            row++;
        }

        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }


        if (row < data.length - 1 && column == data[row].length - 1) {
            int i = column;
            return data[row++][(column -= i) + i];
        }
            return data[row][column++];
    }

    public static void main(String[] args) {
        int[][] in = {
                {1}, {2, 3}, {4, 5, 6}, {7, 8, 9, 10}
        };
        MatrixIt iterator = new MatrixIt(in);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

