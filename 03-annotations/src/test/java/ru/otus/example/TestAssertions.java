package ru.otus.example;

import ru.otus.annotation.Test;


public class TestAssertions {

    @Test
    public void test1() {
        assert true;
    }

    @Test
    public void test2() {
        assert false;
    }
}
