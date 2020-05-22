package ru.otus.model;

import java.lang.reflect.Method;
import java.util.Arrays;

public final class TestResult {

    private final boolean status;
    private final String methodName;
    private String exception;
    private String stackTrace;

    public TestResult(Method method) {
        methodName = method.getName();
        status = true;
    }

    public TestResult(Method method, Exception e) {
        methodName = method.getName();
        status = false;
        exception = e.toString();
        stackTrace = Arrays.toString(e.getStackTrace());
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean getStatus() {
        return status;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getException() {
        return exception;
    }
}
