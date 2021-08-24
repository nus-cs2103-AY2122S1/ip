package duke;

/**
 * Represents exceptions that Duke will throw when the user
 * uses the wrong commands on Duke.
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
