package ru.otus.atm;

import ru.otus.logic.WithdrawAlgorithm;
import ru.otus.operation.ATMOperation;
import ru.otus.operation.model.OperationResult;

import java.util.Objects;

public class ATM {
    static CellManager cellManager;
    static WithdrawAlgorithm withdrawAlgorithm;

    public ATM() {
        cellManager = new CellManager();
    }

    public static CellManager getCellManager() {
        return cellManager;
    }

    public static WithdrawAlgorithm getWithdrawAlgorithm() {
        Objects.requireNonNull(withdrawAlgorithm);
        return withdrawAlgorithm;
    }

    public void setWithdrawAlgorithm(WithdrawAlgorithm withdrawAlgorithm) {
        ATM.withdrawAlgorithm = withdrawAlgorithm;
    }

    public OperationResult operation(ATMOperation operation) {
        return operation.execute();
    }
}
