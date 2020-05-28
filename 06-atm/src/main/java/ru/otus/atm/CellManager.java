package ru.otus.atm;

import ru.otus.exception.CellIsEmptyException;
import ru.otus.money.Bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CellManager {
    private Map<Integer, List<Bill>> cells;

    CellManager() {
        cells = new HashMap<>();
    }

    public void depositMoney(List<Bill> bills) {
        for(Bill bill: bills) {
            cells.putIfAbsent(bill.getDenomination(), new ArrayList<>());
            cells.get(bill.getDenomination()).add(bill);
        }
    }

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

    public long amountOfMoney() {
        return cells
                .entrySet()
                .stream()
                .mapToInt(i -> i.getKey() * i.getValue().size())
                .sum();
    }

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
}
