package ru.otus.atm;

import org.junit.jupiter.api.Test;
import ru.otus.exception.CellIsEmptyException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CellManagerTest {
    @Test
    public void testEmptyCellException() {
        new ATM();
        assertThrows(
                CellIsEmptyException.class,
                () -> ATM.getCellManager().withdrawMoney(new ArrayList<>(Arrays.asList(500, 100)))
        );
    }
}