package bob.exception;

/**
 * Represents the exception thrown by Bob when it receives an unsupported command from the user.
 */
public class InvalidInputException extends BobException {
    /**
     * Constructor for a new InvalidInputException instance.
     */
    public InvalidInputException() {
        super();
    }

    /**
     * Gets error message for when the user input is not one of the supported commands.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "That doesn't make any sense! >:(\n";
    }
}
