package ru.otus;

import ru.otus.example.ExampleTest;

public class Starter {
    public static void main(String[] args) throws Exception {
        TestResultInterpreter.showResults(
                new AnnotationProcessor(ExampleTest.class)
                        .executeTests()
        );
    }
}
