package ru.otus.exception;

public class ClassParseException extends Exception {
    private static final String MESSAGE = "Exception while source object parsing";

    public ClassParseException() {
        super(MESSAGE);
    }

    public ClassParseException(Throwable e) {
        super(MESSAGE, e);
    }
}