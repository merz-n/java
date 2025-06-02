package framework.executions;

import framework.util.AnsiColorCodes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Execution {
    private Class<?> testClass;
    private List<ExecutionItem> executionItems;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Execution(Class<?> testClass, List<ExecutionItem> executionItems, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.testClass = testClass;
        this.executionItems = executionItems;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public List<ExecutionItem> getExecutionItems() {
        return executionItems;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }


    public String toString(String dateTemplate) {
        return "Execution{\n" +
                " testClass:  " + testClass +
                "\n startedAt:  " + startedAt.format(DateTimeFormatter.ofPattern(dateTemplate)) +
                "\n endedAt:  " + endedAt.format(DateTimeFormatter.ofPattern(dateTemplate)) +
                "\n\n-------------------------------------------Test results----------------\n"+
                formatExecutionItems() +
                "}\n";
    }


    private String formatExecutionItems() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var executionItem : executionItems) {
            stringBuilder.append("    ").append(executionItem.toString()).append("\n");
        }

        stringBuilder.append(AnsiColorCodes.WHITE);
        return stringBuilder.toString();
    }
}
