package ru.otus.atm.cell;

import ru.otus.atm.money.Bill;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class CellState {
    private Map<Integer, List<Bill>> cells = new HashMap<>();

    public CellState(Map<Integer, List<Bill>> cells) {
        this.cells = cellMapDeepCopy(cells);
    }

    public CellState(CellManager cellManager) {
        this(cellManager.getCells());
    }

    public CellState(CellState cellState) {
        this(cellState.getCells());
    }

    public static Map<Integer, List<Bill>> cellMapDeepCopy(Map<Integer, List<Bill>> cells) {
        Map<Integer, List<Bill>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Bill>> entry : cells.entrySet())
        {
            copy.put(entry.getKey(), new ArrayList<Bill>(entry.getValue()));
        }
        return copy;
    }

    public Map<Integer, List<Bill>> getCells() {
        return cells;
    }
}
