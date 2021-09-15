package duke.exception;

public class EmptyDescriptionException extends DukeException {

    private static final String message = "Did you forget to write some descriptions after \"%s\"";

    /**
     * Constructs a EmptyDescriptionException instance that handles task with empty description.
     *
     * @param type The task type.
     */
    public EmptyDescriptionException(String type) {
        super(String.format(message, type));
    }
}
