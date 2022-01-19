package ru.job4j.io;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        Pattern pattern = Pattern.compile("404\\s\\d");
        List<String> logs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            logs = in.lines().filter(s -> pattern.matcher(s).find()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String l : log) {
                out.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<String> log = filter("log.txt");

        save(log, "404.txt");
    }
}
