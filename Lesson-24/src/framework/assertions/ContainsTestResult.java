package framework.assertions;

public class ContainsTestResult extends TestResult{
    private String input;
    private String expectedContains;

    public ContainsTestResult(boolean success, String input, String expectedContains) {
        super(success);
        this.input = input;
        this.expectedContains = expectedContains;
    }

    @Override
    public String toString() {
        if(isSuccess())
            return "Input " + input + " doesn't contains string " + expectedContains;
        else
            return "Input " + input + " contains string " + expectedContains;
    }
}
