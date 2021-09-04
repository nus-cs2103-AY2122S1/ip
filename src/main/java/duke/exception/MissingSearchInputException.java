package duke.exception;

/**
 * Represents an exception caused by user not providing search keyword for find command.
 */
public class MissingSearchInputException extends DukeException {
    /**
     * Constructor for MissingSearchInputException.
     */
    public MissingSearchInputException() {
        super("Cannot perform search. Search keyword must be provided");
    }
}
