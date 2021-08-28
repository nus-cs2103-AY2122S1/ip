package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry but my database does not have such command.");
    }
}
