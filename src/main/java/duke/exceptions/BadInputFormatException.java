package duke.exceptions;

public class BadInputFormatException extends InvalidInputException {
    public BadInputFormatException() {
        super("The input is badly formatted.");
    }
}
