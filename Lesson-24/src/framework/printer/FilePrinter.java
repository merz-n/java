package framework.printer;

import framework.executions.Execution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FilePrinter implements Printer{
    private final String filePath;
    private final String template;

    public FilePrinter(String filePath, String template) {
        this.filePath = filePath;
        this.template = template;
    }

    @Override
    public void write(List<Execution> executions) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Execution execution : executions) {
                writer.write("Execution Report");
                writer.newLine();
                writer.write("Test Class: " + execution.getTestClass().getName());
                writer.newLine();
                writer.write("Started At: " + execution.getStartedAt().format(DateTimeFormatter.ofPattern(template)));
                writer.newLine();
                writer.write("Ended At:   " + execution.getEndedAt().format(DateTimeFormatter.ofPattern(template)));
                writer.newLine();
                writer.write("-------------------- Test Results --------------------");
                writer.newLine();

                for (var item : execution.getExecutionItems()) {
                    String status = item.getAssertResult().isSuccess() ? "[✔]" : "[✘]";
                    String methodName = item.getMethod().getName();
                    String result = item.getAssertResult().toString();

                    writer.write(String.format("%s %40s | %20s", status, methodName, result));
                    writer.newLine();
                }

                writer.write("------------------------------------------------------");
                writer.newLine();
                writer.newLine();
            }
            System.out.println("Result in file : " + filePath);

        }catch (IOException e){
            System.err.println("Error write to file: " + e.getMessage());
        }
    }
}
