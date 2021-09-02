package duke.exception;

/** Represents a custom exception class that captures the possible runtime exceptions that may happen in the program. */
public class DukeException extends Exception {
    /** Default DukeException constructor. */
    public DukeException() {
        super();
    }

    /**
     * DukeException constructor.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
