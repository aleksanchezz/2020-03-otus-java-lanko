package ru.otus.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class DIYArrayListJMHAddition {
    private List<Integer> diyArrayList;
    private List<Integer> normalArrayList;

    @Setup
    public void getDataReady() {
        diyArrayList = new DIYArrayList<>();
        normalArrayList = new ArrayList<>();
    }

    @Benchmark
    public void additionDIYArrayList() {
        IntStream.range(1, 10_000).forEach(diyArrayList::add);
    }

    @Benchmark
    public void additionNormalArrayList() {
        IntStream.range(1, 10_000).forEach(normalArrayList::add);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(DIYArrayListJMHAddition.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
