public class Main {
    public static void main(String[] args) {
        FileLoggerConfiguration config = new FileLoggerConfiguration(
                "log/",
                "logfile.log",
                LoggingLevel.DEBUG,
                100,
                "[%s][%s] SMS: %s"
        );

        FileLogger logger = new FileLogger(config);

        logger.info("Hello, world!");
        logger.debug("Your IP-Address: 127.0.0.1");
        logger.info("Second info line.");
        logger.debug("Another debug message with a timestamp: " + System.currentTimeMillis());
        System.out.println("FINISH");


    }
}