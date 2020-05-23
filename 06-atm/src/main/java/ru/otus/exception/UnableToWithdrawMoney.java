package ru.otus.exception;

public class UnableToWithdrawMoney extends Exception {
    public UnableToWithdrawMoney() {
        super("Current cash set in ATM doesn't let withdraw requested amount of money");
    }
}
