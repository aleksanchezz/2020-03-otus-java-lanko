package ru.otus.atm.operation;

import ru.otus.atm.ATM;
import ru.otus.atm.money.Bill;
import ru.otus.atm.model.OperationResult;

import java.util.List;

public class Deposit implements ATMOperation {

    private final List<Bill> bills;
    private final ATM atm;

    public Deposit(List<Bill> bills, ATM atm) {
        this.bills = bills;
        this.atm = atm;
    }

    @Override
    public OperationResult execute() {
        try {
            atm.getCellManager().depositMoney(bills);
            return new OperationResult()
                    .setStatus(Status.SUCCESS)
                    .setBalance(atm.getCellManager().amountOfMoney());
        }
        catch (Exception e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("Monet has been successfully deposited")
                    .setBalance(atm.getCellManager().amountOfMoney());
        }
    }
}
