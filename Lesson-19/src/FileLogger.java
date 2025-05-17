import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class FileLogger implements Logger {
    private FileLoggerConfiguration config;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
    }

    @Override
    public void info(String message) {
        if (!config.level().includes(LoggingLevel.INFO)) return;
        String formatted = formatMessage("INFO", message);
        writeToFile(formatted);

    }

    @Override
    public void debug(String message) {
        if (!config.level().includes(LoggingLevel.DEBUG)) return;
        String formatted = formatMessage("DEBUG", message);
        writeToFile(formatted);

    }

    private String formatMessage(String level, String message) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss"));
        String pattern = config.pattern();
        return String.format(pattern, dateTime, level, message);
    }

   private void writeToFile(String formatted) {
       int messageSize = formatted.getBytes(StandardCharsets.UTF_8).length;
       File file = new File(config.getPath(), config.getFileName());

       if (file.exists() && file.length() + messageSize > config.getMaxFileSize()) {
           String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH-mm-ss"));
           String newFileName = "Log_" + timestamp + ".log";
           file = new File(config.getPath(), newFileName);
           System.out.println("Create new log: " + file.getName());
       }

       file.getParentFile().mkdirs();

       try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
           writer.write(formatted);
           writer.newLine();
           writer.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }

       System.out.println("Write log in file: " + file.getAbsolutePath());
       System.out.println("Content: " + formatted);
   }

}
