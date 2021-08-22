package duke.exception;

public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException() {
        super("A task requires a description following it!");
    }
}
