package duke.exception;

/**
 * Signals that an exception has occurred when saving to storage.
 */
public class SavingException extends DukeException {
    /**
     * Constructs a SavingException.
     */
    public SavingException() {
        super("Error saving tasks.");
    }
}
