package com.exam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileFinder {

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
            if (this.pathPredicate.test(file)) {
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

    public static Predicate<Path> getSearchPredicate(FileFinderArgs args) {
        if (args.searchFlag().equals("-m")) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher(
                    "glob:" + args.searchName()
            );
            return path -> matcher.matches(path.getFileName());
        } else if (args.searchFlag().equals("-f")) {
            return path -> path.toFile().getName().equals(args.searchName());
        } else if (args.searchFlag().equals("-r")) {
            Pattern pat = Pattern.compile(args.searchName());
            return path -> pat.matcher(path.toFile().getName()).matches();
        };
        return null;
    }

    public static List<Path> search(FileFinderArgs args) throws IOException {
        SearchFiles searcher = new SearchFiles(getSearchPredicate(args));
        Files.walkFileTree(args.directoryPath(), searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) {
        // -d "C:/Projects/" -n "*crest*" -m -o "./chapter_002/finder.log"
        // -d "C:/Projects/" -n ".+crest.+" -r -o "./chapter_002/finder.log"
        // -d "C:/Projects/" -n "pom.xml" -f -o "./chapter_002/finder.log"
        if (args.length != 7) {
            throw new IllegalArgumentException("Arguments list not compliments: -d DIRECTORY -n FILE_NAME_MASK {-m, -f, -r} -o OUTPUT");
        }
        FileFinderArgs ffargs = new FileFinderArgs(args);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ffargs.output())))) {
            List<Path> rsl = search(ffargs);
            if (rsl.size() == 0) {
                pw.println("File not found!");
            } else {
                rsl.forEach(pw::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
