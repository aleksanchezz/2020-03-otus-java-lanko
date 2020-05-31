package ru.otus.atm.cell;

import ru.otus.atm.exception.CellIsEmptyException;
import ru.otus.atm.money.Bill;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface CellManager {
    void depositMoney(List<Bill> bills);

    List<Bill> withdrawMoney(List<Integer> billPrescription) throws CellIsEmptyException;

    long amountOfMoney();

    List<Bill> emptyCells();

    Map<Integer, AtomicInteger> getBillsAndAmountInfo();

    Map<Integer, List<Bill>> getCells();

    void setCells(Map<Integer, List<Bill>> cells);
}
