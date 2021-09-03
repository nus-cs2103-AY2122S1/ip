package duke.exception;

/**
 * Thrown when invalid commands are provided by user
 */
public class InvalidEntryException extends DukeException {

    private String error;
    /**
     * Constructor for creating InvalidEntryException object
     *
     * @param msg error message
     */
    public InvalidEntryException(String msg) {
        super(msg);
        this.error = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.println(error);
    }

    @Override
    public String getError() {
        return this.error;
    }
}
