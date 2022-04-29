package ru.job4j.io;


import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        // Создаем лист, в котором будут содержаться листы со стрингами
        List<List<String>> list = new ArrayList<>();
        // Создаем лист Integer, в котором будут храниться индексы названий столбцов, соответствующие значениям, указанным пользователем в качестве фильтра
        ArrayList<Integer> filterIndex = new ArrayList<>();
        String[] userFilter = argsName.get("filter").split(",");

        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                list.add(Arrays.asList(scanner.nextLine().split(argsName.get("delimiter"))));
            }
        }
        List<String> columnNames = list.get(0);

        // Заполняем массив индексами названий столбцов
        for (String s : userFilter) {
            if (columnNames.contains(s)) {
                filterIndex.add(columnNames.indexOf(s));
            }
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            List<String> temp = new ArrayList<>();
            for (List<String> row : list) {
                for (Integer ind : filterIndex) {
                    temp.add(row.get(ind));
                }
                out.write(String.join(";", temp.toArray(new String[0])));
                out.write("\r\n");
                temp.clear();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));

    }
}

