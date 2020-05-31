package ru.otus.atm.operation;

import ru.otus.atm.ATM;
import ru.otus.atm.model.OperationResult;

public class Collect implements ATMOperation {
    private ATM atm;

    public Collect(ATM atm) {
        this.atm = atm;
    }

    @Override
    public OperationResult execute() {
        return new OperationResult()
                .setStatus(Status.SUCCESS)
                .setBills(atm.getCellManager().emptyCells());
    }
}
