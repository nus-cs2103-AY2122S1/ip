package duke.exception;

/**
 * Represents a DukeException that is thrown when the input for adding a Task into a TaskList has an empty description.
 */
public class NoDescriptionException extends DukeException {

    /**
     * Constructs a NoDescriptionException.
     *
     * @param task Task that was attempting to be added into a TaskList which resulted in this Exception.
     */
    public NoDescriptionException(String task) {
        super("A description of the " + task + " is required!");
    }
}
