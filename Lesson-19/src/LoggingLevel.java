public enum LoggingLevel {
    INFO{
        public boolean includes(LoggingLevel other){
            return other == INFO;
        }

    },
    DEBUG{
        public boolean includes(LoggingLevel other){
            return other == INFO || other == DEBUG;
        }

    };
    public abstract boolean includes(LoggingLevel other);

}
