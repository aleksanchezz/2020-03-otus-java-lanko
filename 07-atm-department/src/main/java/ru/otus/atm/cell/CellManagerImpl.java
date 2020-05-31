package ru.otus.atm.cell;

import ru.otus.atm.exception.CellIsEmptyException;
import ru.otus.atm.money.Bill;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CellManagerImpl implements CellManager {
    private Map<Integer, List<Bill>> cells;

    public CellManagerImpl() {
        cells = new HashMap<>();
    }

    @Override
    public void depositMoney(List<Bill> bills) {
        for(Bill bill: bills) {
            cells.putIfAbsent(bill.getDenomination(), new ArrayList<>());
            cells.get(bill.getDenomination()).add(bill);
        }
    }

    @Override
    public List<Bill> withdrawMoney(List<Integer> billPrescription) throws CellIsEmptyException {
        if (cells.isEmpty()) {
            throw new CellIsEmptyException("Empty cells");
        }
        List<Bill> bills = new ArrayList<>();
        for(Integer denomination: billPrescription) {
            bills.add(
                    cells.get(denomination).remove(0)
            );
        }
        return bills;
    }

    @Override
    public long amountOfMoney() {
        return cells
                .entrySet()
                .stream()
                .mapToInt(i -> i.getKey() * i.getValue().size())
                .sum();
    }

    @Override
    public List<Bill> emptyCells() {
        return cells
                .values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, AtomicInteger> getBillsAndAmountInfo() {
        return cells
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> new AtomicInteger(cells.get(entry.getKey()).size())
                ));
    }

    @Override
    public Map<Integer, List<Bill>> getCells() {
        return cells;
    }

    @Override
    public void setCells(Map<Integer, List<Bill>> cells) {
        this.cells = CellState.cellMapDeepCopy(cells);
    }
}
