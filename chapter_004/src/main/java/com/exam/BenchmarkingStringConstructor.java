package com.exam;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BenchmarkingStringConstructor {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        public List<String> list;

        @Setup(Level.Invocation)
        public void setUp() {
            list = Arrays.asList(
                    "d1", "str33", "абв", "фильм"
            );
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    public void make1(ExecutionPlan plan) {
        String rsl = UpperCaseStringConstructor.make1(plan.list);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    public void make1PostUp(ExecutionPlan plan) {
        String rsl = UpperCaseStringConstructor.make1PostUp(plan.list);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    public void make2(ExecutionPlan plan) {
        String rsl = UpperCaseStringConstructor.make2(plan.list);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    public void make2PostUp(ExecutionPlan plan) {
        String rsl = UpperCaseStringConstructor.make2PostUp(plan.list);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    public void make3(ExecutionPlan plan) {
        String rsl = UpperCaseStringConstructor.make3(plan.list);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 2)
    public void make3PostUp(ExecutionPlan plan) {
        String rsl = UpperCaseStringConstructor.make3PostUp(plan.list);
    }

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }
}
