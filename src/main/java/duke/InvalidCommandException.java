package duke;

/**
 * Represents an error when the command is invalid.
 */
public class InvalidCommandException extends DukeException {
    InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of InvalidCommandException.
     *
     * @return The string representation of InvalidCommandException.
     */
    @Override
    public String toString() {
        return "InvalidCommandException: " + getMessage();
    }
}
