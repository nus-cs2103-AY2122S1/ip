package duke.exception;

/**
 * An exception that handles command with missing argument.
 */
public class MissingArgumentException extends DukeException {

    private static final String message = "Did you forgot to include \"%s\" after \"%s\"?";

    /**
     * Constructs an MissingArgumentException instance that handles command with missing argument(s).
     *
     * @param type The type of the task.
     * @param argument The argument used by the command to be functional.
     */
    public MissingArgumentException(String type, String argument) {
        super(String.format(message, argument, type));
    }
}
