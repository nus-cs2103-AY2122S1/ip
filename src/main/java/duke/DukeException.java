package duke;

/**
 * Class used to throw Exceptions.
 */
public class DukeException extends Exception {
    private final String error;

    /**
     * Constructor to initialize DukeException.
     *
     * @param error Error message to be thrown.
     */
    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }
}
