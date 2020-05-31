package ru.otus.department;

import ru.otus.atm.cell.CellState;
import ru.otus.atm.money.Bill;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.atm.money.Bills.*;

public class InitialState {
    static Map<Integer, List<Bill>> stateZeroMap = new HashMap<>();
    static CellState zeroBillState;
    static {
        stateZeroMap.put(5_000, List.of(FIVE_THOUSANDS_RUBLES, FIVE_THOUSANDS_RUBLES, FIVE_THOUSANDS_RUBLES, FIVE_THOUSANDS_RUBLES));
        stateZeroMap.put(1_000, List.of(THOUSAND_RUBLES, THOUSAND_RUBLES, THOUSAND_RUBLES, THOUSAND_RUBLES));
        stateZeroMap.put(500, List.of(FIVE_HUNDREDS_RUBLES, FIVE_HUNDREDS_RUBLES, FIVE_HUNDREDS_RUBLES, FIVE_HUNDREDS_RUBLES));
        stateZeroMap.put(100, List.of(HUNDRED_RUBLES, HUNDRED_RUBLES, HUNDRED_RUBLES, HUNDRED_RUBLES, HUNDRED_RUBLES));
        zeroBillState = new CellState(stateZeroMap);
    }

    public static long calculateStateBalance(CellState state) {
        return state
                .getCells()
                .values()
                .stream()
                .flatMap(Collection::stream)
                .mapToInt(Bill::getDenomination)
                .sum();

    }
}
