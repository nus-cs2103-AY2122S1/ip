package duke.main;

/**
 * Custom exception that represents an exception in Duke.
 */
public class DukeException extends IllegalArgumentException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
