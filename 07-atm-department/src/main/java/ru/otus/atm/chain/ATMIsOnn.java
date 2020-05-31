package ru.otus.atm.chain;

import ru.otus.atm.ATM;
import ru.otus.atm.exception.ATMIsSwitchedOffException;

public class ATMIsOnn extends ATMChain {
    @Override
    public void processInternalChainOperation(ATM atm) throws Exception {
        if (!atm.isSwitched()) {
            throw new ATMIsSwitchedOffException();
        }
    }
}
