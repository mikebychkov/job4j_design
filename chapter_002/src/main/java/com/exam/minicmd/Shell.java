package com.exam.minicmd;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;

class Shell {

    private LinkedList<String> path = new LinkedList<>(Arrays.asList("/"));

    Shell cd(final String path) {
        String[] cdpath = path.split("/");
        for (String cp : cdpath) {
            if (cp.isEmpty() || cp.equals(".")) {
                continue;
            }
            if (cp.equals("..") && this.path.size() > 1) {
                this.path.pollLast();
                continue;
            }
            this.path.add(cp);
        }
        return this;
    }

    public String path() {
        StringJoiner sj = new StringJoiner("/");
        this.path.forEach(sj::add);
        return sj.toString().replace("//", "/");
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString("some text".split("/"))
        );
        StringJoiner sj = new StringJoiner("/");
        System.out.println(sj.toString());
    }
}
