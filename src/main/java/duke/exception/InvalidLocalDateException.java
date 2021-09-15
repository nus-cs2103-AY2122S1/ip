package duke.exception;

/**
 * Represents an exception caused by Duke failing to parse a LocalDate into string format.
 */
public class InvalidLocalDateException extends DukeException {
    /**
     * Constructor for InvalidLocalDateException.
     */
    public InvalidLocalDateException() {
        super("Stored date is corrupt and cannot be read.");
    }
}
