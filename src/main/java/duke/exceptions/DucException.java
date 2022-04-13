package duke.exceptions;

public abstract class DucException extends RuntimeException {
    private static final String DEFAULT_STRING = "Please type in 'help' for instructions";
    /**
     * Constructor for Duke Exception class
     * @param message Error message
     */
    public DucException(String message) {
        super(message);
    }

    /**
     * Indicates the message error for each type of Duke Exception used
     * @return message error of Duke Exception
     */
    @Override
    public String getMessage() {
        return super.getMessage() + DEFAULT_STRING;
    }
}

