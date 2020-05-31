package ru.otus.atm.chain;

import ru.otus.atm.ATM;
import ru.otus.atm.exception.ATMIsEmptyException;
import ru.otus.atm.operation.ATMOperation;
import ru.otus.atm.operation.Balance;

public class ATMIsEmptyChain extends ATMChain {

    @Override
    public void processInternalChainOperation(ATM atm) throws Exception {
        ATMOperation balanceCheckOperation = new Balance(atm);
        if (atm.operation(balanceCheckOperation).getBalance() < 0) {
            throw new ATMIsEmptyException();
        }
    }
}
