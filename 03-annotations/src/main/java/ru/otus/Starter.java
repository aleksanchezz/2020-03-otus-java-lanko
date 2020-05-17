package ru.otus;

import ru.otus.example.ExampleTest;
import ru.otus.processor.AnnotationProcessor;
import ru.otus.processor.TestClassProcessor;

public class Starter {
    public static void main(String[] args) throws Exception {
        TestResultInterpreter.showResults(
                new TestClassProcessor(
                        new AnnotationProcessor(ExampleTest.class)
                                .process()
                )
                        .executeTests()
        );
    }
}
