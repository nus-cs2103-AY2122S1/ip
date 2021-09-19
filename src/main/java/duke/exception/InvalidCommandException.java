package duke.exception;

/**
 * Signals that the user has entered an invalid command.
 */
class InvalidCommandException extends Exception {

    /**
     * Creates an `InvalidCommandException` with the specified message.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
