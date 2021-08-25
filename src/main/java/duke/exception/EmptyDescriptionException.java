package duke.exception;

/**
 * Signals that the description is missing when parsing a command.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs an EmptyDescriptionException for the given task.
     *
     * @param task Task that is missing a description.
     */
    public EmptyDescriptionException(String task) {
        super("The description of a " + task + " cannot be empty.");
    }
}
