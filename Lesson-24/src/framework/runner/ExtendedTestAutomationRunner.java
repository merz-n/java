package framework.runner;

import framework.printer.Printer;
import framework.executions.Execution;
import java.util.*;

public class ExtendedTestAutomationRunner implements Runner{
    private final List<Class<?>> testClasses;
    private final Set<Printer> printers;

    public ExtendedTestAutomationRunner(List<Class<?>> testClasses, Set<Printer> printers) {
        this.testClasses = testClasses;
        this.printers = new HashSet<>(printers);
    }

    @Override
    public void run() {
        List<Execution> executions = new ArrayList<>();

        for (Class<?> testClass : testClasses) {
            TestExecutor executor = new TestExecutor(testClass);
            Execution execution = executor.run();
            executions.add(execution);
        }

        for (Printer printer : printers) {
            printer.write(executions);
        }

    }
}
