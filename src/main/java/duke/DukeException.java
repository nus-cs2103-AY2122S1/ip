package duke;

/**
 * Handles exceptions for when user input is invalid.
 */
public class DukeException extends Exception {
    public DukeException(String str) {
        super(str);
    }
}
