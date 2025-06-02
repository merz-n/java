package framework.executions;

import framework.annotation.Test;
import framework.assertions.AssertResult;
import framework.assertions.TestResult;
import framework.util.AnsiColorCodes;

import java.lang.reflect.Method;

public class ExecutionItem {
    private Class<?> testClass;
    private Method method;
    private TestResult assertResult;

    public ExecutionItem(Class<?> testClass, Method method, TestResult assertResult) {
        this.testClass = testClass;
        this.method = method;
        this.assertResult = assertResult;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public Method getMethod() {
        return method;
    }

    public TestResult getAssertResult() {
        return assertResult;
    }

    @Override
    public String toString() {
        String code = assertResult.isSuccess() ? AnsiColorCodes.GREEN : AnsiColorCodes.RED;
        return code +  String.format("%40s | %s", method.getName(), " assertResult=" + assertResult);
    }
}
