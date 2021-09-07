package duke.exceptions;

/**
 * The DukeException class is the parent class of all the other runtime exceptions thrown.
 * All the other runtime errors return respective string outputs as the message
 */
public class DukeException extends RuntimeException {

    /**
     * Initialises the input error message
     *
     * @param message input error message
     */
    public DukeException(String message) {
        super(message);
    }

}
