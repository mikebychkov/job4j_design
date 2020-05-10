package com.io;

import java.io.File;

public class ArgZip {

    private boolean valid;
    private String errors = "";
    private String dir = "";
    private String excl = "";
    private String out = "";

    public ArgZip(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-d")) {
                this.dir = args[i + 1];
            }
            if (args[i].equals("-e")) {
                this.excl = args[i + 1];
            }
            if (args[i].equals("-o")) {
                this.out = args[i + 1];
            }
        }
    }

    public boolean valid() {
        this.valid = true;
        if (this.dir.isEmpty() || this.excl.isEmpty() || this.out.isEmpty()) {
            this.valid = false;
            this.errors = "Arguments not set -d DIRECTORY -e EXCLUDED_EXTENSIONS -o OUTPUT";
        } else {
            File argFile = new File(this.dir);
            if (!argFile.exists()) {
                this.valid = false;
                this.errors = "Directory is not exist!";
            } else if (!argFile.isDirectory()) {
                this.valid = false;
                this.errors = "Directory is not a catalog!";
            }
        }
        return this.valid;
    }

    public String directory() {
        return this.dir;
    }

    public String exclude() {
        return this.excl;
    }

    public String output() {
        return this.out;
    }

    public String errors() {
        return this.errors;
    }
}
