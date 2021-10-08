package duke;

/**
 * Used for error handling in application
 */
public class DukeException extends Exception {
    public DukeException(String description) {
        super(description);
    }
}
