package duke.exception;

/**
 * Signals that an exception has occurred when loading the storage.
 */
public class LoadingException extends DukeException {
    /**
     * Constructs a LoadingException.
     */
    public LoadingException() {
        super("Error loading tasks.");
    }
}
