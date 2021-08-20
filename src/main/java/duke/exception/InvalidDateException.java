package duke.exception;

public class InvalidDateException extends InvalidInputException {
    public InvalidDateException() {
        super("This is not a valid date!");
    }
}
