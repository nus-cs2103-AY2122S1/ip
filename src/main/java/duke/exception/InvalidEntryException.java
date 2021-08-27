package duke.exception;

/**
 * Thrown when invalid commands are provided by user
 */
public class InvalidEntryException extends DukeException {

    /**
     * Constructor for creating InvalidEntryException object
     *
     * @param msg error message
     */
    public InvalidEntryException(String msg) {
        super(msg);
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
