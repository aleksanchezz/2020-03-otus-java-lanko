package ru.otus.atm.model;

import ru.otus.atm.money.Bill;
import ru.otus.atm.operation.Status;

import java.util.List;

public class OperationResult {
    private List<Bill> bills;
    private Status status;
    private String Message;
    private long balance;

    public OperationResult() {
    }

    public List<Bill> getBills() {
        return bills;
    }

    public OperationResult setBills(List<Bill> bills) {
        this.bills = bills;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public OperationResult setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return Message;
    }

    public OperationResult setMessage(String message) {
        Message = message;
        return this;
    }

    public long getBalance() {
        return balance;
    }

    public OperationResult setBalance(long balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public String toString() {
        return "OperationResult{" +
                "bills=" + bills +
                ", status=" + status +
                ", Message='" + Message + '\'' +
                ", balance=" + balance +
                '}';
    }
}
