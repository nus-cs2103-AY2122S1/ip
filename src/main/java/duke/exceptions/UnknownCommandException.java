package duke.exceptions;

/**
 * Encapsulates the UnknownCommandException thrown by the Duke bot.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor for UnknownInputException.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means.");
    }
}
