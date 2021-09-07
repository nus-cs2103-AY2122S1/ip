package duke.dukeexception;

/**
 * Represents an exception for cases where todo is called but no description follows it.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs the EmptyDescriptionException from a message.
     *
     * @param message The given error message.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
