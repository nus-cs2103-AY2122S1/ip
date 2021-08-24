package duke.dukeexception;

/**
 * Represents an exception when an illegal command is given by a user.
 */
public class IllegalCommandException extends DukeException {
    /**
     * Constructs the IllegalCommandException from an error message.
     *
     * @param message The given error message.
     */
    public IllegalCommandException(String message) {
        super(message);
    }
}
