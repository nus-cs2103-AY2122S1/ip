package duke.exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException() {
        super("Invalid input.");
    }
}
