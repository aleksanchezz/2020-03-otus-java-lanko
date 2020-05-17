package ru.otus.processor;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;
import ru.otus.model.TestClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**Immutable*/
public class AnnotationProcessor {

    private final Class<?> clazz;
    private final List<Method> testMethods = new ArrayList<>();

    private final List<Method> beforeMethods = new ArrayList<>();
    private final List<Method> afterMethods = new ArrayList<>();

    public AnnotationProcessor(Class<?> classToProcess) throws Exception {
        clazz = Class.forName(classToProcess.getName());
    }

    public TestClass process() throws Exception {
        findAnnotatedMethodsInClass();
        if (testMethods.isEmpty()) {
            throw new NoSuchMethodException("No tests in " + clazz.getName());
        }
        return new TestClass().newBuilder()
                .setClass(clazz)
                .setBeforeMethods(beforeMethods)
                .setAfterMethods(afterMethods)
                .setTestMethods(testMethods)
                .build();
    }

    private void findAnnotatedMethodsInClass() {
        Method[] methods = clazz.getMethods();
        for(Method method: methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
    }
}
