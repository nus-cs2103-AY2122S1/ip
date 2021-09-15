package duke.exception;

/**
 * A class that handles command with extraneous argument.
 */
public class ExtraArgumentException extends DukeException {

    private static final String message = "Do not write anything after \"%s\" !";
    /**
     * Constructs an ExtraArgumentException instance that handles command with extraneous argument.
     *
     * @param commandType The type of command.
     */
    public ExtraArgumentException(String commandType) {
        super(String.format(message, commandType));
    }
}
