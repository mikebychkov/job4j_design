package com.gc;

public class User {

    private static long count = 0;

    private long id;
    private String name;
    private long[] weight;

    public User() {
        System.out.printf("create: %s%n", this);
    }

    public User(int weight) {
        this.id = count++;
        this.name = String.format("User #%s", id);
        this.weight = new long[weight];
        for (int i = 0; i < weight; i++) {
            this.weight[i] = i;
        }
        System.out.printf("create: %s%n", this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("finalize: %s%n", this);
    }

    public static void iter(int i) {
        for (int x = 0; x <= i; x++) {
            new User(10000);
        }
        info();
    }

    public static void main(String[] args) {
        User uEmpty = new User();
        info();
        User uInit = new User(10000);
        info();
        //
        iter(20);
        iter(20);
    }

    public static void info() {
        System.out.println("=".repeat(50));
        double mb = 1024.0 * 1024.0;
        Runtime rt = Runtime.getRuntime();
        System.out.printf("Max: %s%n", rt.maxMemory() / mb);
        System.out.printf("Total: %s%n", rt.totalMemory() / mb);
        System.out.printf("Free: %s%n", rt.freeMemory() / mb);
        System.out.printf("Used: %s%n", (rt.totalMemory() - rt.freeMemory()) / mb);
        System.out.println("=".repeat(50));
    }
}
