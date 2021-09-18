package duke;

/**
 * Creates exceptions caused by Duke program.
 */
public class DukeException extends Exception {

    /**
     * Initialises exceptions with custom messages.
     *
     * @param message that shows why exception was thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
