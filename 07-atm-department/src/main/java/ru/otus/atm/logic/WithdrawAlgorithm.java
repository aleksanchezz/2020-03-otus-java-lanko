package ru.otus.atm.logic;

import ru.otus.atm.exception.InvalidAmountRequested;
import ru.otus.atm.exception.UnableToWithdrawMoney;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface WithdrawAlgorithm {
    List<Integer> getPrescription(long amount, Map<Integer, AtomicInteger> denominationAndCount)
            throws InvalidAmountRequested, UnableToWithdrawMoney;
}
