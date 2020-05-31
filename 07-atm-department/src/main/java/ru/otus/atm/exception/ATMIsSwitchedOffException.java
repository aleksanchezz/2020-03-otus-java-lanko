package ru.otus.atm.exception;

public class ATMIsSwitchedOffException extends Exception {
    public ATMIsSwitchedOffException() {
        super("ATM is switched off");
    }
}
