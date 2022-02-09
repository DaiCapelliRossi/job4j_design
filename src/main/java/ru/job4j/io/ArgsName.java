package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key does not exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String key = arg.substring((arg.indexOf("-") + 1), arg.indexOf("="));
            String value = arg.substring(arg.indexOf("=") + 1);
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Some parameters are missing");
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}