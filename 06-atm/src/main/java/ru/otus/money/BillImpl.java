package ru.otus.money;

public class BillImpl implements Bill {

    private final int denomination;
    private final Currency currency;

    public BillImpl(int denomination, Currency currency) {
        this.denomination = denomination;
        this.currency = currency;
    }

    @Override
    public int getDenomination() {
        return denomination;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Bill{" + denomination + " of " + currency + '}';
    }
}
