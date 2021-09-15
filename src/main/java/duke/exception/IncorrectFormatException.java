package duke.exception;

public class IncorrectFormatException extends DukeException {
    public IncorrectFormatException() {
        super("Incorrect format for command.");
    }
}
