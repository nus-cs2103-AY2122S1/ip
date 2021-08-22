package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm not too sure what you mean :(");
    }
}
