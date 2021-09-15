package duke.exception;

public class NoNumberException extends DukeException {
    public NoNumberException() {
        super("Please enter a number after the command.");
    }
}
