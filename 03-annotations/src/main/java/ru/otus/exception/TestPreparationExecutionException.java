package ru.otus.exception;

public class TestPreparationExecutionException extends Exception {
    public static final String DEFAULT_MESSAGE = "Exception while test preparation methods execution";

    public TestPreparationExecutionException(Exception e) {
        super(DEFAULT_MESSAGE, e);
    }

    public TestPreparationExecutionException(String message, Exception e) {
        super(message, e);
    }
}
