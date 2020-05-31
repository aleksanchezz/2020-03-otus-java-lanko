package ru.otus.atm.exception;

public class ATMIsEmptyException extends Exception {
    public ATMIsEmptyException() {
        super("ATM is empty");
    }
}
