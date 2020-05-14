package com.exam;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileFinderArgs {

    private boolean valid;
    private String errors = "";
    private String dir = "";
    private Path dirPath;
    private String searchName = "";
    private String searchFlag = "";
    private String out = "";

    public FileFinderArgs(String[] args) {
        int index = 0;
        Pattern pat = Pattern.compile("-[mfr]");
        while (index < args.length) {
            if (args[index].equals("-d")) {
                this.dir = args[index + 1];
                this.dirPath = Paths.get(this.dir);
                index += 2;
            }
            if (args[index].equals("-n")) {
                this.searchName = args[index + 1];
                index += 2;
            }
            if (pat.matcher(args[index]).matches()) {
                this.searchFlag = args[index];
                index++;
            }
            if (args[index].equals("-o")) {
                this.out = args[index + 1];
                index += 2;
            }
        }
        if (!valid()) {
            throw new IllegalArgumentException(this.errors);
        }
    }

    public boolean valid() {
        this.valid = true;
        // ARGUMENTS NOT EMPTY CHECK
        if (this.dir.isEmpty() ||
                this.searchName.isEmpty() ||
                this.searchFlag.isEmpty() ||
                this.out.isEmpty()
        ) {
            this.valid = false;
            this.errors = "Arguments not set -d DIRECTORY -n FILE_NAME_MASK {-m, -f, -r} -o OUTPUT";
            return this.valid;
        }
        // DIRECTORY CHECK
        File argFile = new File(this.dir);
        if (!argFile.exists()) {
            this.valid = false;
            this.errors = "DIRECTORY is not exist!";
        } else if (!argFile.isDirectory()) {
            this.valid = false;
            this.errors = "DIRECTORY is not a catalog!";
        }
        // REGEXP CHECK
        if (this.searchFlag.equals("-r")) {
            try {
                Pattern pat = Pattern.compile(this.searchName);
            } catch (PatternSyntaxException e) {
                this.valid = false;
                this.errors = "FILE_NAME_MASK is not a regular expression pattern!";
            }
        }
        return this.valid;
    }

    public String directory() {
        return this.dir;
    }

    public Path directoryPath() {
        return this.dirPath;
    }

    public String searchName() {
        return this.searchName;
    }

    public String searchFlag() {
        return this.searchFlag;
    }

    public String output() {
        return this.out;
    }

    public String errors() {
        return this.errors;
    }
}
