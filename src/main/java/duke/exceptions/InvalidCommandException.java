package duke.exceptions;

/**
 * The condition where the user input does not belong to any valid
 * Duke command.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Creates an invalid command exception instance with a standard detail message.
     */
    public InvalidCommandException() {
        super("I'm not sure what you mean. (T_T)");
    }
}
