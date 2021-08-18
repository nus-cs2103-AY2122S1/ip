package duke.exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Input is invalid");
    }

    protected InvalidInputException(String message) {
        super(message);
    }
}
