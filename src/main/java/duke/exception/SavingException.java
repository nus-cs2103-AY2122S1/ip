package duke.exception;

/**
 * Signals that an exception has occurred when saving to storage.
 */
public class SavingException extends DukeException {
    /**
     * Constructs a SavingException.
     */
    public SavingException() {
        super("Duke's memory full liao, can't memorize your new task liao!!");
    }
}
