package ru.otus.processor;

import ru.otus.exception.TestExecutionException;
import ru.otus.exception.TestPreparationExecutionException;
import ru.otus.model.TestClass;
import ru.otus.model.TestResult;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class TestClassProcessor {

    private final TestClass testClass;
    private Object objectToProcess;

    public TestClassProcessor(TestClass testClass) {
        this.testClass = testClass;
    }

    private Object initTestClassInstance() throws Exception {
        return testClass
                .getClazz()
                .getDeclaredConstructor()
                .newInstance();
    }

    /**
     * Исполнить (если есть) методы аннотированные @Before, затем исполнить сам тест, затем, также опционально -
     * методы, аннотированные @After
     */
    public List<TestResult> executeTests() throws Exception {
        List<TestResult> testResults = new ArrayList<>();
        for (Method method: testClass.getTestMethods()) {
            try {
                objectToProcess = initTestClassInstance();
                invokeBeforeMethods();
                invokeTestMethod(method);
                invokeAfterMethods();
                testResults.add(new TestResult(method));
            }
            catch (TestExecutionException e) {
                testResults.add(new TestResult(method, e));
                invokeAfterMethods();
            }
            catch (TestPreparationExecutionException e) {
                throw new RuntimeException("Exception while @before/@after methods execution - terminate");
            }
        }
        return testResults;
    }

    private void invokeBeforeMethods() throws TestPreparationExecutionException {
        try {
            invokeListOfMethods(testClass.getBeforeMethods());
        }
        catch (Exception e) {
            throw new TestPreparationExecutionException("Exception in @before method[s]", e);
        }
    }

    private void invokeTestMethod(Method testMethod) throws TestExecutionException {
        try {
            invokeMethod(testMethod);
        }
        catch (Exception e) {
            throw new TestExecutionException("Test exception " + testMethod.getName(), e);
        }
    }

    private void invokeAfterMethods() throws TestPreparationExecutionException {
        try {
            invokeListOfMethods(testClass.getAfterMethods());
        }
        catch (Exception e) {
            throw new TestPreparationExecutionException("Exception in @after method[s]", e);
        }
    }

    private void invokeListOfMethods(List<Method> methods) throws Exception {
        for(Method method: methods) {
            invokeMethod(method);
        }
    }

    private void invokeMethod(Method method) throws Exception {
        method.invoke(objectToProcess);
    }
}
