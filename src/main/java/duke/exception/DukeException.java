package duke.exception;


/**
 * Abstract class representing a exception that could occur due to Duke.
 */
public abstract class DukeException extends Exception {
    public DukeException(String errMessage) {
        super(errMessage);
    }
}
