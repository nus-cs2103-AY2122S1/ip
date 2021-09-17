package duke.exception;

/**
 * Represents a DukeException that is thrown when an input to search for a task is missing what
 * to search for.
 */
public class MissingKeywordException extends DukeException {

    /**
     * Constructor for MissingKeywordException.
     */
    public MissingKeywordException() {
        super("Boss, I think you need to tell me what exactly to find!");
    }
}
