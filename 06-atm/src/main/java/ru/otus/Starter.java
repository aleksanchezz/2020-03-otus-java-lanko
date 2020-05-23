package ru.otus;

import ru.otus.atm.ATM;
import ru.otus.logic.SimpleWithdrawManager;
import ru.otus.money.Bill;
import ru.otus.money.Bills;
import ru.otus.operation.ATMOperation;
import ru.otus.operation.Balance;
import ru.otus.operation.Deposit;
import ru.otus.operation.Withdraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {
        ATM atm = new ATM();
        atm.setWithdrawAlgorithm(new SimpleWithdrawManager());

        List<Bill> billsToDeposit = new ArrayList<>(
                Arrays.asList(
                        Bills.THOUSAND_RUBLES,
                        Bills.FIVE_HUNDREDS_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES
                )
        );

        ATMOperation depositOperation = new Deposit(billsToDeposit);
        System.out.println(atm.operation(depositOperation));

        ATMOperation withdrawOperation = new Withdraw(800);
        System.out.println(atm.operation(withdrawOperation));

        ATMOperation balanceOperation = new Balance();
        System.out.println(atm.operation(balanceOperation));
    }
}
