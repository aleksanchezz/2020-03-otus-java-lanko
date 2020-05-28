package ru.otus.logic;

import ru.otus.exception.InvalidAmountRequested;
import ru.otus.exception.UnableToWithdrawMoney;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface WithdrawAlgorithm {
    List<Integer> getPrescription(long amount, Map<Integer, AtomicInteger> denominationAndCount)
            throws InvalidAmountRequested, UnableToWithdrawMoney;
}
