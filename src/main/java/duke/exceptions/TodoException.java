package duke.exceptions;

/**
 * Exception that is thrown when a Todo class cannot be created due to empty description provided by user.
 */
public class TodoException extends DukeException {
    /**
     * Creates an object of the TodoException class.
     */
    public TodoException() {
        super();
    }

    /**
     * Gets the message to be printed in response to the error.
     *
     * @return String message in response to the error thrown.
     */
    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a todo cannot be empty.";
    }
}
