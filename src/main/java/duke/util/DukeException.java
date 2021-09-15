package duke.util;

/**
 * Class to handle exceptions.
 */
public class DukeException extends Exception {
    /**
     * Acts as the constructor for the exception.
     *
     * @param error The error message of the exception.
     */
    public DukeException(String error) {
        super(error);
    }
}
