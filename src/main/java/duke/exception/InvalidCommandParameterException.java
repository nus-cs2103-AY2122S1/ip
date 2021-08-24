package duke.exception;

/**
 * Represents an exception caused when an unrecognized parameter is used for a recognized command.
 */
public class InvalidCommandParameterException extends DukeException {
    /**
     * Constructor for exception
     */
    public InvalidCommandParameterException() {
        super("Oops, you have entered an invalid parameter for this command");
    }
}
