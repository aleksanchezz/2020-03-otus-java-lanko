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
public class DIYArrayListJMHSorting {

    private List<Integer> diyArrayList;
    private List<Integer> normalArrayList;

    @Setup
    public void getDataReady() {
        diyArrayList = new DIYArrayList<>();
        normalArrayList = new ArrayList<>();
        Random random = new Random();
        IntStream.range(1_000, 200_000).forEach(i -> diyArrayList.add(random.nextInt()));
        normalArrayList = Arrays.asList(new Integer[diyArrayList.size()]);
        Collections.copy(normalArrayList, diyArrayList);
    }

    @Benchmark
    public void sortDIYArrayList() {
        Collections.sort(diyArrayList);
    }

    @Benchmark
    public void sortNormalArrayList() {
        Collections.sort(normalArrayList);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(DIYArrayListJMHSorting.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
