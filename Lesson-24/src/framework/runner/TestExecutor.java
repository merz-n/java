package framework.runner;

import framework.annotation.Test;
import framework.assertions.AssertException;
import framework.assertions.AssertResult;
import framework.assertions.Assertions;
import framework.assertions.EmptyTestResult;
import framework.executions.Execution;
import framework.executions.ExecutionItem;
import framework.printer.Printer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestExecutor {
    private final Class<?> testClass;

    public TestExecutor(Class<?> testClass) {
        this.testClass = testClass;
    }
    public Execution run() {
        Object testInstance = createTestInstance();

        LocalDateTime startDateTime = LocalDateTime.now();
        List<ExecutionItem> executionItems = new ArrayList<>();

        for (Method method : testClass.getMethods()) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }

            executionItems.add(invokeTestMethod(method, testInstance));
        }

        LocalDateTime endDateTime = LocalDateTime.now();

        return new Execution(testClass, executionItems, startDateTime, endDateTime);
    }

    private Object createTestInstance() {
        try {
            Constructor<?> constructor = testClass.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Test class must have a public no-args constructor: " + testClass.getName(), e);
        }
    }

    private ExecutionItem invokeTestMethod(Method method, Object instance) {
        if (method.getParameterCount() > 0) {
            throw new IllegalArgumentException("Test method must not have parameters: " + method.getName());
        }

        try {
            method.invoke(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access method: " + method.getName(), e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AssertException assertException) {
                return new ExecutionItem(testClass, method, assertException.getAssertResult());
            }
            throw new RuntimeException("Unexpected exception during test execution: " + method.getName(), e);
        }

        return new ExecutionItem(testClass, method, EmptyTestResult.EMPTY_SUCCESS);
    }

}
