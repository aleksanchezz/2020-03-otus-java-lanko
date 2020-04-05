package ru.otus;

import com.google.common.math.IntMath;

import java.util.stream.IntStream;

public class HelloOtus {
    public static void main(String[] args) {
        int facts = IntStream
                .range(0, 10)
                .map(IntMath::factorial)
                .sum();
        System.out.println("0! + 1! + ... + 10! = " + facts);
    }
}
