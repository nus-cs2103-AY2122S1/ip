package duke.exceptions;

/**
 * Models the exceptions handled by Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
