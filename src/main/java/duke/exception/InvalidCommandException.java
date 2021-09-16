package duke.exception;

/** Exception to be thrown when a command is invalid */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor for a InvalidCommandException
     */
    public InvalidCommandException() {
        super("Sorry I don't understand this command.");
    }
}
