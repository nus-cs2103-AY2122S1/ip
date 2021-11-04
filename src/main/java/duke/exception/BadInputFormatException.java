package duke.exception;

/** Used when the user's input does not follow the expected format. */
public class BadInputFormatException extends InvalidInputException {
    public BadInputFormatException() {
        super("The input is badly formatted.");
    }
}
