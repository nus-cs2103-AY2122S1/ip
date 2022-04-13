package duke;

/**
 * Custom exception for duke to be printed out
 */
public class DukeException extends Exception {

    /**
     * Creates new duke exception with message
     *
     * @param message Error message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Gets the message of the duke exception
     *
     * @return
     */
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
