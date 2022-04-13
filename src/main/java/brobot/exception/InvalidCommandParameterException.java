package brobot.exception;

/**
 * Represents an exception caused when an unrecognized parameter is used for a recognized command.
 */
public class InvalidCommandParameterException extends BroException {
    /**
     * Constructor for exception
     */
    public InvalidCommandParameterException() {
        super("Bro i think you put in the wrong parameters for this command");
    }
}
