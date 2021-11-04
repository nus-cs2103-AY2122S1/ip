package duke.exception;

/** Used when the user's input is empty or only contains whitespace. */
public class EmptyCommandException extends InvalidInputException {
    public EmptyCommandException() {
        super("Command input cannot be empty!");
    }
}
