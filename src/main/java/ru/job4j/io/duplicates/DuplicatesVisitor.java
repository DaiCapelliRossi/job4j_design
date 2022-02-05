package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<Path, FileProperty> map = new LinkedHashMap<>();
    private final Map<Path, FileProperty> duplicates = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        map.put(file.toAbsolutePath(), new FileProperty(attrs.size(), file.getFileName().toString()));
        return super.visitFile(file, attrs);
    }

    public Set<Path> getPaths() {
        for (Map.Entry<Path, FileProperty> mapEntry : map.entrySet()) {
            for (Map.Entry<Path, FileProperty> entry : map.entrySet()) {
                if ((!(entry.getKey().equals(mapEntry.getKey()))) && entry.getValue().equals(mapEntry.getValue())) {
                    duplicates.put(mapEntry.getKey(), mapEntry.getValue());
                    duplicates.put(entry.getKey(), entry.getValue());
                }
            }
        }
        for (Path path : duplicates.keySet()) {
            System.out.println(path);
        }

        return duplicates.keySet();
    }
}