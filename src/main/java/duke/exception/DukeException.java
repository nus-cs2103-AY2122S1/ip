package duke.exception;

/**
 * Duke Exception is thrown when any exception occurs in Duke.
 */
public class DukeException extends Exception {

    public DukeException(String errMessage) {
        super(errMessage);
    }
}
