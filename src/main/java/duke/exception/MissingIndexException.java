package duke.exception;

/**
 * Represents a DukeException that is thrown when an input to refer to a task is missing which
 * task it is referring to.
 */
public class MissingIndexException extends DukeException {

    /**
     * Constructor for MissingIndexException.
     */
    public MissingIndexException() {
        super("Hmmm... Boss I think you need to clarify which task you are referring to!");
    }
}
