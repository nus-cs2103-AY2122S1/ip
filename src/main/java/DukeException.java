/**
 * Represents the parent exception class that is thrown when
 * there is an error with Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructor for the parent exception class.
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
