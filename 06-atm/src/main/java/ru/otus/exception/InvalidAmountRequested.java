package ru.otus.exception;

public class InvalidAmountRequested extends Exception {
    public InvalidAmountRequested() {
        super("Requested amount is not a multiple of minimal bill for this currency");
    }
}
