package tokio.exceptions;

/**
 * Throws exception when user input causes problems.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param str Error message for DukeException.
     */
    public DukeException (String str) {
        super("===== ERROR =====\n" + str);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
