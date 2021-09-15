package duke.exception;

public class LoadFileException extends DukeException {
    public LoadFileException() {
        super("Unable to load tasks from file.");
    }
}
