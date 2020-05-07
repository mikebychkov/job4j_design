package com.io;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test1() {
        String path1 = "server.log";
        String path2 = "unavailable.csv";

        Analizy an = new Analizy();
        an.unavailable(path1, path2);

        assertThat(an.getFileStrings(path2).get(0), is("10:57:01;10:59:01;"));
    }

    @Test
    public void test2() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        String sourcePath = source.getAbsolutePath();
        String targetPath = target.getAbsolutePath();

        List<String> list = new ArrayList<>();
        list.add("200 10:56:01");
        list.add("500 10:57:01");
        list.add("400 10:58:01");
        list.add("200 10:59:01");
        list.add("500 11:01:02");
        list.add("200 11:02:02");

        Analizy an = new Analizy();
        an.writeFileStrings(sourcePath, list);
        an.unavailable(sourcePath, targetPath);

        assertThat(
                an.getFileStrings(targetPath).get(0),
                is("10:57:01;10:59:01;")
        );
    }
}