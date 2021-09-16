package duke.exception;

/**
 * Class that encapsulates an exception thrown when user enters a command with the wrong format.
 */
public class WrongCommandFormatException extends Exception {

    private String message;

    /**
     * Constructor for the exception
     * @param message The error message that duke would return to the user.
     */
    public WrongCommandFormatException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Gets the exception message.
     * @return The exception message.
     */
    public String getMessage() {
        return this.message;
    }
}
