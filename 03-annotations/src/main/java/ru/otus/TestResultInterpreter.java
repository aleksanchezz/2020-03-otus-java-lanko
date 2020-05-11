package ru.otus;

import ru.otus.model.TestResult;

import java.util.List;
import java.util.function.Predicate;

public final class TestResultInterpreter {

    private TestResultInterpreter() {

    }

    public static void showResults(List<TestResult> testResults) {
        long successful = testResults
                .stream()
                .filter(TestResult::getStatus)
                .count();
        long failed = testResults.size() - successful;
        System.out.println("Tests statistics:\n" +
                "Successful: " + successful + "\n" +
                "Failed: " + failed);
        if (failed > 0) {
            System.out.println("\nFailed tests:");
            testResults
                    .stream()
                    .filter(Predicate.not(TestResult::getStatus))
                    .forEach(i -> {
                        System.out.println("Test: " + i.getMethodName());
                        System.out.println("\t" + i.getException());
                        System.out.println("\t" + i.getStackTrace());
                    });
        }
    }
}
