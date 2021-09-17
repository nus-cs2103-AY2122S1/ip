package duke.exception;

/**
 * Represents a DukeException that is thrown when the input to add a task does not have a description.
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Constructor for MissingDescriptionException.
     */
    public MissingDescriptionException() {
        super("Might want to recheck that boss! The description of a task cannot be empty!");
    }
}
