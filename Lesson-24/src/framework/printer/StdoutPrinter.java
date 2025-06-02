package framework.printer;

import framework.executions.Execution;

import java.util.List;

public class StdoutPrinter implements Printer{
    private final String template;

    public StdoutPrinter(String template) {
        this.template = template;
    }

    public void write(List<Execution> executions) {
        executions.forEach(execution -> {
            System.out.println(execution.toString(template));
        });
    }
}
