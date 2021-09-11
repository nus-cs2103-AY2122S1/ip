package duke.exception;

/**
 * Class that encapsulates an loading error Duke-specific exception.
 */
public class LoadingError extends DukeException {

    /**
     * Constructs a LoadingError instance.
     *
     * @param message Loading error message
     */
    public LoadingError(String message) {
        super("Loading Error: " + message);
    }
}
