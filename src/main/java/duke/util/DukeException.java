package duke.util;

/**
 * Class to handle exceptions
 */
public class DukeException extends Exception {
    /**
     * Constructor for the exception
     *
     * @param error The error message of the exception
     */
    public DukeException(String error) {
        super(error);
    }
}