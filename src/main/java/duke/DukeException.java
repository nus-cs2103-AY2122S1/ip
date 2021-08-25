package duke;

/**
 * A class of Exception that can be thrown for invalid Duke behaviours.
 */
public class DukeException extends Exception {

    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }
}
