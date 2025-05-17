public class FileLoggerConfiguration implements LoggerConfiguration{
    String path;
    String fileName;
    LoggingLevel level;
    Integer maxFileSize;
    String pattern;

    public FileLoggerConfiguration(String path, String fileName, LoggingLevel level, Integer maxFileSize, String pattern) {
        this.path = path;
        this.fileName = fileName;
        this.level = level;
        this.maxFileSize = maxFileSize;
        this.pattern = pattern;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public LoggingLevel level(){
        return level;
    };
    public String pattern(){
        return pattern;
    }
}
