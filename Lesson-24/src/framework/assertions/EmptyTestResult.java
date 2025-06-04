package framework.assertions;

public class EmptyTestResult extends TestResult{
    public static final EmptyTestResult EMPTY_SUCCESS = new EmptyTestResult(true);
    private EmptyTestResult(boolean success){
        super(success);
    }

    @Override
    public String toString() {
        return "EXECUTED";
    }
}
