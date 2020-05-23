package ru.otus.operation;

import ru.otus.atm.ATM;
import ru.otus.operation.model.OperationResult;

public class Balance implements ATMOperation {
    public Balance() {

    }

    @Override
    public OperationResult execute() {
        return new OperationResult()
                .setStatus(Status.SUCCESS)
                .setBalance(ATM.getCellManager().amountOfMoney());
    }
}
