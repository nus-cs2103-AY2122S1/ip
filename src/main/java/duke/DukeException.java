package duke;

/**
 * A class to handle all Exceptions.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException object
     *
     * @param e the error message
     */
    public DukeException(String e) {
        super(e);
    }

    /**
     * Gets the error message
     *
     * @return the error message
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
