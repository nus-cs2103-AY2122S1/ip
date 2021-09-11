package duke.exception;

/**
 * Class that encapsulates an missing description Duke-specific exception.
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Constructs a MissingDescriptionException instance.
     */
    public MissingDescriptionException() {
        super("A task requires a description following it!");
    }
}
