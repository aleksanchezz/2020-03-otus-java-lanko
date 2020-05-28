package ru.otus.operation;

import ru.otus.atm.ATM;
import ru.otus.money.Bill;
import ru.otus.operation.model.OperationResult;

import java.util.List;

public class Deposit implements ATMOperation {

    private final List<Bill> bills;

    public Deposit(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public OperationResult execute() {
        try {
            ATM.getCellManager().depositMoney(bills);
            return new OperationResult()
                    .setStatus(Status.SUCCESS)
                    .setBalance(ATM.getCellManager().amountOfMoney());
        }
        catch (Exception e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("Monet has been successfully deposited")
                    .setBalance(ATM.getCellManager().amountOfMoney());
        }
    }
}
