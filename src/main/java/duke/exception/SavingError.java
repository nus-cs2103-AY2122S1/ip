package duke.exception;

public class SavingError extends DukeException {
    public SavingError(String message) {
        super("Saving Error: " + message);
    }
}
