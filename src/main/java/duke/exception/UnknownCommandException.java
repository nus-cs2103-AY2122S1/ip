package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Nee doesn't understand your command!");
    }
}
