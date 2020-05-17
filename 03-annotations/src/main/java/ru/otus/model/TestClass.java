package ru.otus.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestClass {

    private Class<?> clazz;
    private List<Method> testMethods = new ArrayList<>();

    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();

    public Class<?> getClazz() {
        return clazz;
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {
        public Builder setClass(Class<?> clazz) {
            TestClass.this.clazz = clazz;
            return this;
        }

        public Builder setTestMethods(List<Method> testMethods) {
            TestClass.this.testMethods = testMethods;
            return this;
        }

        public Builder setBeforeMethods(List<Method> beforeMethods) {
            TestClass.this.beforeMethods = beforeMethods;
            return this;
        }

        public Builder setAfterMethods(List<Method> afterMethods) {
            TestClass.this.afterMethods = afterMethods;
            return this;
        }

        public TestClass build() {
            return TestClass.this;
        }
    }
}
