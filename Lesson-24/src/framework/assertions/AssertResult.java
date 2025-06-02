package framework.assertions;

public class AssertResult<T> extends TestResult {
    private final T expected;
    private final T actual;

    public AssertResult(boolean success, T expected, T actual) {
        super(success);
        this.expected = expected;
        this.actual = actual;
    }

    public T getExpected() {
        return expected;
    }

    public T getActual() {
        return actual;
    }
    @Override
    public String toString() {
        return "{expected=" + expected +
                ", actual=" + actual +
                '}';
    }
}
