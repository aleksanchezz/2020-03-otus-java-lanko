package ru.otus.atm.chain;

import ru.otus.atm.ATM;

public abstract class ATMChain {
    private ATMChain nextChain;

    public void process(ATM atm) throws Exception{
        processInternalChainOperation(atm);
        if (hasNext()) {
            nextChain.process(atm);
        }
    }

    public abstract void processInternalChainOperation(ATM atm) throws Exception;

    public boolean hasNext() {
        return nextChain != null;
    }

    public void setNext(ATMChain next) {
        this.nextChain = next;
    }
}
