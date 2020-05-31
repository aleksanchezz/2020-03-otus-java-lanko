package ru.otus.department;

import ru.otus.atm.ATM;
import ru.otus.atm.cell.CellState;
import ru.otus.atm.chain.ATMChain;
import ru.otus.atm.chain.ATMIsOnn;
import ru.otus.atm.money.Bill;
import ru.otus.atm.operation.*;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.department.InitialState.zeroBillState;

public class Department {
    // listeners
    private List<ATM> atms = new ArrayList<>();
    private ATMChain checkChain;

    public Department() {
        checkChain = new ATMIsOnn();
        checkChain.setNext(new ATMIsOnn());
    }

    public void addATM(ATM atm) {
        atms.add(atm);
    }

    public void removeATM(ATM atm) {
        atms.remove(atm);
    }

    /**
     * Инкассация - забрать все деньги из банкомата и разместить там ZeroState набор купюр
     */
    public List<Bill> collectMoney() throws Exception {
        List<Bill> revenue = new ArrayList<>();
        for (ATM atm: atms) {
            checkChain.process(atm);
            ATMOperation collectMoneyFromATMOperation = new Collect(atm);
            atm.operation(collectMoneyFromATMOperation);

            atm.getCellManager().setCells(zeroBillState.getCells());
        }
        return revenue;
    }

    public void loadZeroState() throws Exception {
        loadState(zeroBillState);
    }

    public void loadState(CellState cellState) throws Exception {
        for (ATM atm: atms) {
            checkChain.process(atm);
            atm.getCellManager().setCells(cellState.getCells());
        }
    }

    public long checkBalance() throws Exception {
        long result = 0;
        for(ATM atm: atms) {
            checkChain.process(atm);
            ATMOperation balanceOperation = new Balance(atm);
            result += atm.operation(balanceOperation).getBalance();
        }
        return result;
    }
}
