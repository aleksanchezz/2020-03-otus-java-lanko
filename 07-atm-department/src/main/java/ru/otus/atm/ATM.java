package ru.otus.atm;

import ru.otus.atm.cell.CellManager;
import ru.otus.atm.logic.WithdrawAlgorithm;
import ru.otus.atm.operation.ATMOperation;
import ru.otus.atm.model.OperationResult;

public interface ATM {

    CellManager getCellManager();

    void setWithdrawAlgorithm(WithdrawAlgorithm withdrawAlgorithm);

    WithdrawAlgorithm getWithdrawAlgorithm();

    OperationResult operation(ATMOperation operation);

    boolean isSwitched();
}
