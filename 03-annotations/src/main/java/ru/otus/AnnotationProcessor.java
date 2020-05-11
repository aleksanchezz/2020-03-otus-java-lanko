package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;
import ru.otus.model.TestResult;

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
        findAnnotatedMethodsInClass();
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

    private Object initTestClassInstance() throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }

    private static void invokeMethod(Method method, Object object) throws Exception {
        method.invoke(object);
    }

    /**
     * Исполнить (если есть) методы аннотированные @Before, затем исполнить сам тест, затем, также опционально -
     * методы, аннотированные @After
     */
    private void invokeTestMethod(Method testMethod) throws Exception {
        Object objectToProcess = initTestClassInstance();
        if (!beforeMethods.isEmpty()) {
            for(Method method: beforeMethods) {
                invokeMethod(method, objectToProcess);
            }
        }
        invokeMethod(testMethod, objectToProcess);
        if (!afterMethods.isEmpty()) {
            for(Method method: afterMethods) {
                invokeMethod(method, objectToProcess);
            }
        }
    }

    public List<TestResult> executeTests() throws Exception {
        List<TestResult> testResults = new ArrayList<>();
        if (testMethods.isEmpty()) {
            throw new NoSuchMethodException("No tests in " + clazz.getName());
        }
        for (Method method: testMethods) {
            try {
                invokeTestMethod(method);
                testResults.add(new TestResult(method));
            }
            catch (Exception e) {
                testResults.add(new TestResult(method, e));
            }
        }
        return testResults;
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
}
