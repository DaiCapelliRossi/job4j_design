package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analizy {

    private final ArrayList<List<String>> list = new ArrayList<>();
    boolean isWorking;
    StringBuilder sb = new StringBuilder();

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(l -> list.add(Arrays.asList(l.split(" "))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            if (list.get(0).get(0).equals("200") || list.get(0).get(0).equals("300")) {
                isWorking = true;
            } else {
                sb.append(list.get(0).get(1)).append(";");
                isWorking = false;
            }
            for (List<String> l : list) {
                if ((isWorking) && (l.get(0).equals("400") || l.get(0).equals("500"))) {
                    sb.append(l.get(1)).append(";");
                    isWorking = false;
                } else if ((!isWorking) && (l.get(0).equals("200") || l.get(0).equals("300"))) {
                    sb.append(l.get(1));
                    out.println(sb);
                    sb.setLength(0);
                    isWorking = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy1 = new Analizy();
        analizy1.unavailable("example1.csv", "example1outcome.csv");
        Analizy analizy2 = new Analizy();
        analizy2.unavailable("example2.csv", "example2outcome.csv");
    }
}
