package duke.exception;

/** Exception to be thrown when a command has an incorrect format */
public class IncorrectFormatException extends DukeException {

    /**
     * Constructor for a IncorrectFormatException
     */
    public IncorrectFormatException() {
        super("Incorrect format for command.");
    }
}
