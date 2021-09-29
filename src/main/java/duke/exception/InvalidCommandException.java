package duke.exception;

/**
 * Exception class to handle Invalid/non-existent commands.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor for InvalidCommandException class.
     */
    public InvalidCommandException() {
        super("Sorry >.< I do not understand your command!");
    }
}
