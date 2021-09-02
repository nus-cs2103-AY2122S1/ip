package duke.exception;

/** Used when the input date does not follow the expected format. */
public class InvalidDateException extends InvalidInputException {
    public InvalidDateException() {
        super("This is not a valid date!");
    }
}
