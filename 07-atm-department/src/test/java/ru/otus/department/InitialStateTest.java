package ru.otus.department;

import org.junit.jupiter.api.Test;
import ru.otus.atm.cell.CellState;
import ru.otus.atm.money.Bill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.atm.money.Bills.*;

class InitialStateTest {

    @Test
    public void testCalculator() {
        Map<Integer, List<Bill>> testCells = new HashMap<>();
        testCells.put(1_000, List.of(THOUSAND_RUBLES));
        testCells.put(100, List.of(HUNDRED_RUBLES, HUNDRED_RUBLES));
        CellState testState = new CellState(testCells);
        assertThat(InitialState.calculateStateBalance(testState)).isEqualTo(1200);
    }
}