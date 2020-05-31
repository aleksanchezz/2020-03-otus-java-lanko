package ru.otus.atm.logic;

import ru.otus.atm.exception.InvalidAmountRequested;
import ru.otus.atm.exception.UnableToWithdrawMoney;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class SimpleWithdrawManager implements WithdrawAlgorithm {
    private Map<Integer, AtomicInteger> denominationAndCount;

    public SimpleWithdrawManager() {

    }

    Integer getCurrentMinimalDenomination() {
        return denominationAndCount
                .keySet()
                .stream()
                .min(Comparator.naturalOrder())
                .orElseThrow(RuntimeException::new);
    }

    Integer getCurrentMaximalDenomination() {
        return denominationAndCount
                .keySet()
                .stream()
                .min(Comparator.reverseOrder())
                .orElseThrow(RuntimeException::new);
    }

    Integer getBillCount(Integer billDenomination) {
        return denominationAndCount
                .get(billDenomination)
                .get();
    }

    public List<Integer> getPrescription(long amount, Map<Integer, AtomicInteger> denominationAndCount)
            throws InvalidAmountRequested, UnableToWithdrawMoney {
        this.denominationAndCount = denominationAndCount;
        return getPrescription(amount);
    }

    public List<Integer> getPrescription(long amount)
            throws InvalidAmountRequested, UnableToWithdrawMoney {
        Objects.requireNonNull(denominationAndCount);
        int minimalBillDenomination = getCurrentMinimalDenomination();

        if (amount % minimalBillDenomination != 0) {
            throw new InvalidAmountRequested();
        }
        List<Integer> result = new ArrayList<>();
        long leftover = amount;
        while (leftover >= minimalBillDenomination) {
            int currentMaxBillDenomination = getCurrentMaximalDenomination();
            int currentBillCount = denominationAndCount.get(currentMaxBillDenomination).get();

            if (leftover >= currentMaxBillDenomination && currentBillCount >= 1) {
                denominationAndCount.get(currentMaxBillDenomination).decrementAndGet();
                leftover -= currentMaxBillDenomination;
                result.add(currentMaxBillDenomination);
            }
            else {
                denominationAndCount.remove(currentMaxBillDenomination);
            }
            if (denominationAndCount.isEmpty() && leftover > 0) {
                throw new UnableToWithdrawMoney();
            }
        }
        return result;
    }

    public SimpleWithdrawManager setDenominationAndCount(Map<Integer, AtomicInteger> denominationAndCount) {
        this.denominationAndCount = denominationAndCount;
        return this;
    }
}
