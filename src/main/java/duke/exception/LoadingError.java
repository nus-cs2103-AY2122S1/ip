package duke.exception;

public class LoadingError extends DukeException {
    public LoadingError(String message) {
        super("Loading Error: " + message);
    }
}
