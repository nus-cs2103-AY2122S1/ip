package workdone.exception;

/**
 * Represents an exception thrown when commands received are unknown. A subclass of WorkDoneException.
 */
public class InvalidCommandException extends WorkDoneException {
    /**
     * Constructor of the class `InvalidCommandException`.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
