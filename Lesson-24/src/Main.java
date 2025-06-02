import framework.printer.FilePrinter;
import framework.printer.StdoutPrinter;
import framework.runner.TestAutomationRunner;
import program.test.CalculatorTest;
import framework.runner.Runner;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // new TestAutomationRunner(List.of(CalculatorTest.class), new StdoutPrinter("yyyy-MM-dd HH:mm:ss")).run();
        List<Class<?>> classes = List.of(CalculatorTest.class);
        var stdoutPrinter = new StdoutPrinter("yyyy-MM-dd HH:mm:ss");
        var filePrinter = new FilePrinter("test-report.txt", "yyyy-MM-dd HH:mm:ss");

        new TestAutomationRunner(classes, stdoutPrinter).run();
        new TestAutomationRunner(classes, filePrinter).run();
    }
}