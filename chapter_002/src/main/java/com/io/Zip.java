package com.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {

            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
        */
        ArgZip az = new ArgZip(args);
        if (!az.valid()) {
            throw new IllegalArgumentException(az.errors());
        }

        File outFile = new File(az.output());
        Path start = Paths.get(az.directory());
        List<File> files = Search.searchExcludeExtensions(start, az.exclude()).stream().map(
                path -> path.toFile()
        ).collect(Collectors.toList());

        packFiles(files, outFile);
    }
}
