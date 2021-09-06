package duke.exception;

public class InvalidDukeCommandException extends RuntimeException {
    private static final String preMessage = "ERROR: ";

    /**
     * Constructs a new instance of InvalidDukeCommandException that handles instances of invalid Duke commands.
     *
     * @param message the message that corresponds to the exception.
     */
    public InvalidDukeCommandException(String message) {
        super(preMessage + message);
    }
}
