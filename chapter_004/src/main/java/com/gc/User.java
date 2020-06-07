package com.gc;

/** GC Types
 * Serial (-XX:+UseSerialGC)
 * Parallel (-XX:+UseParallelGC)
 * Parallel compacting (-XX:+UseParallelOldGC)
 * Concurrent mark-sweep (CMS) (-XX:+UseConcMarkSweepGC)
 * G1 (-XX:+UseG1GC)
 */

/** GC Statistics
 * -Xms1m (Heap init size)
 * -Xmx64m (Heap max size)
 * -Xlog:gc (PrintGC)
 * -Xlog:gc* (PrintGCDetails)
 * -Xlog:task*=debug (PrintGCTaskTimeStamps)
 * -Xlog:safepoint (PrintGCApplicationStoppedTime, PrintGCApplicationConcurrentTime)
 *
 * -Xlog:all=info:stdout:uptime,levels,tags
 * -Xlog:gc=debug:file=gc.txt:none
 * -Xlog:gc=trace:file=gctrace.txt:uptimemillis,pids:filecount=5,filesize=1024
 * -Xlog:gc::uptime,tid
 * -Xlog:gc,safepoint
 * -Xlog:::time,level,tags
 * -Xlog:gc*,safepoint::uptime,time
 * -Xlog:gc=trace,safepoint::time,uptime
 * -Xlog:gc*,safepoint::uptime,time,level,tags
 *
 * https://docs.oracle.com/javase/9/tools/java.htm#GUID-BE93ABDC-999C-4CB5-A88B-1994AAAC74D5__CONVERTGCLOGGINGFLAGSTOXLOG-A5046BD1
 */

/** GC Profiling
 * > jps
 * ... (id)
 * > jmap -heap (id)
 * > jmap -dump
 * > jstack (id)
 * > jconsole
 */

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
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("finalize: %s%n", this);
    }

    public static void iter(int i) {
        for (int x = 0; x <= i; x++) {
            new User(10000 * x);
        }
        info();
    }

    public static void main(String[] args) {
        info();
        User uEmpty = new User();
        info();
        User uInit = new User(10000);
        info();
        //
        iter(20);
        iter(20);
    }

    public static void info() {
        /*
        System.out.println("=".repeat(50));
        double mb = 1024.0 * 1024.0;
        Runtime rt = Runtime.getRuntime();
        System.out.printf("Max: %s%n", rt.maxMemory() / mb);
        System.out.printf("Total: %s%n", rt.totalMemory() / mb);
        System.out.printf("Free: %s%n", rt.freeMemory() / mb);
        System.out.printf("Used: %s%n", (rt.totalMemory() - rt.freeMemory()) / mb);
        System.out.println("=".repeat(50));
        */
    }
}
