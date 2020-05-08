package com.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        //Files.walkFileTree(start, new PrintFiles());
        search(start, "js").forEach(System.out::println);
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

    public static List<Path> searchExcludeExtensions(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
