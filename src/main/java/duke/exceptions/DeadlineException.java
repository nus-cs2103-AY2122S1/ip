package duke.exceptions;

/**
 * Exception that is thrown when a Deadline class cannot be created due to empty description provided by user.
 */
public class DeadlineException extends DukeException {
    /**
     * Creates an object of the DeadlineException class.
     */
    public DeadlineException() {
        super();
    }

    /**
     * Gets the message to be printed in response to the error.
     *
     * @return String message in response to the error thrown.
     */
    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a deadline cannot be empty.";
    }
}
