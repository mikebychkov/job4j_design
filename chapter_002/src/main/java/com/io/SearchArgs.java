package com.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchArgs {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER TARGET_EXTENSION");
        }
        String fileName = args[0];
        String fileExtension = args[1];
        Path start = Paths.get(fileName);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException("File does not exists.");
        }
        search(start, fileExtension).forEach(System.out::println);
    }

    public static class SearchFiles implements FileVisitor<Path> {
        Predicate<Path> pathPredicate;
        List<Path> listOfPaths = new ArrayList<>();

        public SearchFiles(Predicate<Path> pathPredicate) {
            this.pathPredicate = pathPredicate;
        }

        public List<Path> getPaths() {
            return this.listOfPaths;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (pathPredicate.test(file)) {
                this.listOfPaths.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
