package duke.exception;

/**
 * Thrown when invalid commands are provided by user
 */
public class InvalidEntryException extends DukeException {

    /**
     * Constructor for creating InvalidEntryException object
     */
    public InvalidEntryException() {
        super();
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        String error = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        return error;
    }
}
