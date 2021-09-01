package duke.exceptions;

/**
 * Wrapper Exception to be used in program to catch and display expected errors.
 */
public class DukeException extends Exception {
    public static final String ERROR_UNEXPECTED = "Error: Something unexpected happened. Please submit a bug report.";

    public DukeException() {

    }

    public DukeException(String message) {
        super(message);
    }

}
