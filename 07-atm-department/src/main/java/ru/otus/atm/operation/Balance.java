package ru.otus.atm.operation;

import ru.otus.atm.ATM;
import ru.otus.atm.model.OperationResult;

public class Balance implements ATMOperation {
    private final ATM atm;

    public Balance(ATM atm) {
        this.atm = atm;
    }

    @Override
    public OperationResult execute() {
        return new OperationResult()
                .setStatus(Status.SUCCESS)
                .setBalance(atm.getCellManager().amountOfMoney());
    }
}
