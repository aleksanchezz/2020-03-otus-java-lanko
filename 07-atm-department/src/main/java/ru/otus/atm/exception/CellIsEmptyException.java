package ru.otus.atm.exception;

public class CellIsEmptyException extends Exception {
    public CellIsEmptyException(String message) {
        super(message);
    }
}
