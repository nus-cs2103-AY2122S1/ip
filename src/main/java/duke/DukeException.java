package duke;

/**
 * Exception specifically related to Duke program.
 */
public class DukeException extends Exception {
    private final String message;

    /**
     * Instantiates DukeException.
     *
     * @param message Error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the string representation of this Exception.
     *
     * @return Error message specified.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
