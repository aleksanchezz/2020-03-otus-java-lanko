package ru.otus.money;

public class Bills {
    // rubles section
    public static final Bill HUNDRED_RUBLES = new BillImpl(100, Currency.RUBLE);
    public static final Bill TWO_HUNDREDS_RUBLES = new BillImpl(200, Currency.RUBLE);
    public static final Bill FIVE_HUNDREDS_RUBLES = new BillImpl(500, Currency.RUBLE);
    public static final Bill THOUSAND_RUBLES = new BillImpl(1_000, Currency.RUBLE);
    public static final Bill TWO_THOUSANDS_RUBLES = new BillImpl(2_000, Currency.RUBLE);
    public static final Bill FIVE_THOUSANDS_RUBLES = new BillImpl(5_000, Currency.RUBLE);
}
