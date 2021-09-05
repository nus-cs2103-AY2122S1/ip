package duke;

/**
 * Represents an error when the description is empty.
 */
public class EmptyDescriptionException extends DukeException {
    EmptyDescriptionException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of EmptyDescriptionException.
     *
     * @return The string representation of EmptyDescriptionException.
     */
    @Override
    public String toString() {
        return "EmptyDescriptionException: " + getMessage();
    }
}
