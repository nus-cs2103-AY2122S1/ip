package duke.exception;

/** Exception to be thrown when a number is expected after a command but is not present */
public class NoNumberException extends DukeException {

    /**
     * Constructor for a NoNumberException
     */
    public NoNumberException() {
        super("Please enter a number after the command.");
    }
}
