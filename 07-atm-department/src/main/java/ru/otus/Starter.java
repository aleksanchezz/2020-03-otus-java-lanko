package ru.otus;

import ru.otus.atm.ATM;
import ru.otus.atm.ATMImpl;
import ru.otus.atm.logic.SimpleWithdrawManager;
import ru.otus.department.Department;

public class Starter {
    public static void main(String[] args) throws Exception {
        ATM atm1 = new ATMImpl();
        atm1.setWithdrawAlgorithm(new SimpleWithdrawManager());

        ATM atm2 = new ATMImpl();
        atm1.setWithdrawAlgorithm(new SimpleWithdrawManager());

        ATM atm3 = new ATMImpl();
        atm1.setWithdrawAlgorithm(new SimpleWithdrawManager());

        Department department = new Department();
        department.addATM(atm1);
        department.addATM(atm2);
        department.addATM(atm3);

        department.loadZeroState();
        System.out.println(department.checkBalance());
    }
}
