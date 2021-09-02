package duke.exception;

/** A class of exceptions that relates to the invalidity of the user's input. */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Input is invalid");
    }

    protected InvalidInputException(String message) {
        super(message);
    }
}
