package duke.exception;

/**
 * An exception that handles command with incomplete description.
 */
public class IncompleteDescriptionException extends DukeException {

    private static final String message = "Write some descriptions at both sides of \"%s\"!";
    /**
     * Constructs an IncompleteDescriptionException instance that handles command with incomplete description.
     *
     * @param type The task type.
     */
    public IncompleteDescriptionException(String type) {
        super(String.format(message, type));
    }
}
