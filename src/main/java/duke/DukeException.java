package duke;

/**
 * Represents an Exception thrown by Duke.
 */
public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}
