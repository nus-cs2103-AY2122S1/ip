package duke.exception;

/**
 * Thrown when user does not enter task number to delete a task or mark a task as completed
 */
public class NotATaskNumberException extends DukeException {

    /**
     * Constructs NotATaskNumberException object
     *
     */
    public NotATaskNumberException() {
        super();
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        String error = "OOPS!!! Please enter a task number!";
        return error;
    }
}
