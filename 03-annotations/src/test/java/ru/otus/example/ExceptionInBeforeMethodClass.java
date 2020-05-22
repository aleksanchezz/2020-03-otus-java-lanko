package ru.otus.example;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class ExceptionInBeforeMethodClass {
    @Before
    public void setUp() {
        System.out.println("Setup method");
        throw new RuntimeException("Because I can:)");
    }

    @After
    public void tearDown() {
        System.out.println("Tear Down method");
    }

    @Test
    public void test1() {
        System.out.println("Test #1");
    }
}
