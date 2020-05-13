package com.io;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ConsoleChat {
    private boolean silentMode;
    private boolean exitMode;
    private Random rnd = new Random();
    private List<String> answers = new ArrayList<>();
    private List<String> log = new LinkedList<>();
    private String logFile = "";
    private final String stopCommand = "стоп";
    private final String continueCommand = "продолжить";
    private final String exitCommand = "закончить";

    public ConsoleChat(String answersFile, String logFile) {
        File ansFl = new File(answersFile);
        if (!ansFl.exists()) {
            throw new IllegalArgumentException("No such answers file!");
        }
        this.answers = readAnswers(answersFile);
        this.logFile = logFile;
    }

    public void start() throws IOException {
        Scanner input = new Scanner(System.in);
        while (!exitMode) {
            String userIn = input.nextLine();
            this.log.add(userIn);
            if (stopCommand.equals(userIn)) {
                this.silentMode = true;
            } else if (continueCommand.equals(userIn)) {
                this.silentMode = false;
            } else if (exitCommand.equals(userIn)) {
                this.exitMode = true;
                continue;
            }
            if (this.silentMode) {
                continue;
            }
            String outMsg = this.answers.get(this.rnd.nextInt(this.answers.size()));
            this.log.add(outMsg);
            System.out.println(outMsg);
        }
        writeLog();
    }

    public List<String> readAnswers(String filename) {
        List<String> out = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(filename))) {
            //read.lines().map(ln -> ln.replace(".", ",")).flatMap(
            //        ln -> Stream.of(ln.split(","))
            read.lines().flatMap(ln -> Stream.of(ln.split("[,.]"))
            ).forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    private void writeLog() {
        if (this.logFile.isEmpty()) {
            return;
        }
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream(this.logFile)
        )) {
            for (String str : this.log) {
                out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Argument not specified ANSWERS_FILE LOG_FILE");
        }
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.start();
    }
}
