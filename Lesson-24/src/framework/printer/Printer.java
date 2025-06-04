package framework.printer;

import framework.executions.Execution;

import java.util.List;

public interface Printer {
    void write(List<Execution> executions);
}
