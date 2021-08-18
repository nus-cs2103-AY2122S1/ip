package duke.exceptions;

public class EmptyCommandException extends InvalidInputException {
    public EmptyCommandException() {
        super("duke.commands.Command input cannot be empty!");
    }
}
