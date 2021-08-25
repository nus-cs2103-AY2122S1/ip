package duke;

/**
 * Represents an Exception thrown by duke.Duke.
 */
public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}
