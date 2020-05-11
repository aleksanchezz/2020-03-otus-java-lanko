package ru.otus.example;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;


public class ExampleTest {

    @Before
    public void setUp() {
        System.out.println("Setup method");
    }

    @After
    public void tearDown() {
        System.out.println("Tear Down method");
    }

    @Test
    public void test1() {
        System.out.println("Test #1");
    }

    @Test
    public void test2() {
        System.out.println("Test #2");
    }
}
