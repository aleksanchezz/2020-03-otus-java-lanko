package ru.otus.atm.operation;

import ru.otus.atm.ATM;
import ru.otus.atm.exception.CellIsEmptyException;
import ru.otus.atm.exception.InvalidAmountRequested;
import ru.otus.atm.exception.UnableToWithdrawMoney;
import ru.otus.atm.money.Bill;
import ru.otus.atm.model.OperationResult;

import java.util.List;

public class Withdraw implements ATMOperation {
    private final long moneyAmount;
    private final ATM atm;

    public Withdraw(long moneyAmount, ATM atm) {
        this.moneyAmount = moneyAmount;
        this.atm = atm;
    }

    @Override
    public OperationResult execute() {
        try {
            List<Integer> billPrescription = atm.getWithdrawAlgorithm()
                    .getPrescription(moneyAmount, atm.getCellManager().getBillsAndAmountInfo());
            List<Bill> bills = atm.getCellManager().withdrawMoney(billPrescription);
            return new OperationResult()
                    .setBills(bills)
                    .setStatus(Status.SUCCESS)
                    .setMessage(moneyAmount + " has been withdrawn successfully")
                    .setBalance(atm.getCellManager().amountOfMoney());
        }
        catch (InvalidAmountRequested  e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("Requested amount is wrong, sorry")
                    .setBalance(atm.getCellManager().amountOfMoney());
        }
        catch (UnableToWithdrawMoney e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("ATM is about to get empty, sorry")
                    .setBalance(atm.getCellManager().amountOfMoney());
        }
        catch (CellIsEmptyException e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("Not enough money here -> ask manager")
                    .setBalance(atm.getCellManager().amountOfMoney());
        }
    }
}
