package ru.otus.atm;

import ru.otus.atm.cell.CellManager;
import ru.otus.atm.cell.CellManagerImpl;
import ru.otus.atm.logic.WithdrawAlgorithm;
import ru.otus.atm.operation.ATMOperation;
import ru.otus.atm.model.OperationResult;

import java.util.Objects;

public class ATMImpl implements ATM {
    private CellManager cellManager;
    private boolean isSwitched = false;
    private WithdrawAlgorithm withdrawAlgorithm;

    public ATMImpl() {
        cellManager = new CellManagerImpl();
        isSwitched = true;
    }

    @Override
    public CellManager getCellManager() {
        return cellManager;
    }

    @Override
    public WithdrawAlgorithm getWithdrawAlgorithm() {
        Objects.requireNonNull(withdrawAlgorithm);
        return withdrawAlgorithm;
    }

    @Override
    public void setWithdrawAlgorithm(WithdrawAlgorithm withdrawAlgorithm) {
        this.withdrawAlgorithm = withdrawAlgorithm;
    }

    @Override
    public OperationResult operation(ATMOperation operation) {
        return operation.execute();
    }

    @Override
    public boolean isSwitched() {
        return isSwitched;
    }
}
