package ru.otus.example;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class MultipleBeforeAndAfterClass {

    @Before
    public void setUpNumberOne() {
        System.out.println("Setup method1");
    }

    @Before
    public void setUpNumberTwo() {
        System.out.println("Setup method1");
    }

    @Test
    public void test1() {
        System.out.println("Test #1");
    }

    @Test
    public void test2() {
        System.out.println("Test #2");
    }

    @Test
    public void complexTestNumberThree() {
        System.out.println("Test #3");
    }

    @After
    public void tearDownOne() {
        System.out.println("Tear Down method 1");
    }

    @After
    public void tearDownTwo() {
        System.out.println("Tear Down method 2");
    }
}
