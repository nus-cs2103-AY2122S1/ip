package duke.exception;

/**
 * Class that encapsulates a saving error Duke-specific exception.
 */
public class SavingError extends DukeException {

    /**
     * Constructs a SavingError instance.
     *
     * @param message Saving error message
     */
    public SavingError(String message) {
        super("Saving Error: " + message);
    }
}
