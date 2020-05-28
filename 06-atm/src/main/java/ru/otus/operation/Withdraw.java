package ru.otus.operation;

import ru.otus.atm.ATM;
import ru.otus.exception.CellIsEmptyException;
import ru.otus.exception.InvalidAmountRequested;
import ru.otus.exception.UnableToWithdrawMoney;
import ru.otus.money.Bill;
import ru.otus.operation.model.OperationResult;

import java.util.List;

public class Withdraw implements ATMOperation {
    private final long moneyAmount;

    public Withdraw(long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public OperationResult execute() {
        try {
            List<Integer> billPrescription = ATM.getWithdrawAlgorithm()
                    .getPrescription(moneyAmount, ATM.getCellManager().getBillsAndAmountInfo());
            List<Bill> bills = ATM.getCellManager().withdrawMoney(billPrescription);
            return new OperationResult()
                    .setBills(bills)
                    .setStatus(Status.SUCCESS)
                    .setMessage(moneyAmount + " has been withdrawn successfully")
                    .setBalance(ATM.getCellManager().amountOfMoney());
        }
        catch (InvalidAmountRequested  e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("Requested amount is wrong, sorry")
                    .setBalance(ATM.getCellManager().amountOfMoney());
        }
        catch (UnableToWithdrawMoney e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("ATM is about to get empty, sorry")
                    .setBalance(ATM.getCellManager().amountOfMoney());
        }
        catch (CellIsEmptyException e) {
            return new OperationResult()
                    .setStatus(Status.FAILURE)
                    .setMessage("Not enough money here -> ask manager")
                    .setBalance(ATM.getCellManager().amountOfMoney());
        }
    }
}
