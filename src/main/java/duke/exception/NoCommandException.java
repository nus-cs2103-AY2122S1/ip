package duke.exception;

public class NoCommandException extends DukeException {
    public NoCommandException() {
        super("OOPS! I'm sorry, but I don't know what that means :(");
    }
}
