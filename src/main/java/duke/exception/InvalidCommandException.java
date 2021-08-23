package duke.exception;

/**
 * Exception class to handle the Invalid commands.
 *
 * @author limzk126
 * @version Level-7
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for InvalidCommandException class.
     */
    public InvalidCommandException() {
        super("Sorry >.< I do not understand your command!");
    }
}
