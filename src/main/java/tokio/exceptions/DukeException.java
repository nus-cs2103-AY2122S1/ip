package tokio.exceptions;

/**
 * Exception thrown when user input causes problems.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param str Error message for DukeException.
     */
    public DukeException (String str) {
        super(str);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
