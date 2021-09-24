package duke;

/**
 * Represents exceptions that will be thrown when Duke runs.
 */
public class DukeException extends Exception {

    protected String errorMessage;

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMessage Error message that describes the error that occurred.
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns String representation of DukeException.
     *
     * @return String representation of DukeException.
     */
    @Override
    public String toString() {
        return ":(( sorry bud but " + this.errorMessage;
    }
}
