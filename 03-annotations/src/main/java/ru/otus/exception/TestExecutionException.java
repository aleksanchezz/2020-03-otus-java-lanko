package ru.otus.exception;

public class TestExecutionException extends Exception {
    public TestExecutionException(String message, Exception e) {
        super(message, e);
    }
}
