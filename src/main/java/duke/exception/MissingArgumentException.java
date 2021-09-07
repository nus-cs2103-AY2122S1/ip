package duke.exception;

/**
 * An exception that handles command with missing argument.
 */
public class MissingArgumentException extends DukeException {

    private static final String errorMessage = "Some arguments are missing in the [%s] command. "
            + "Check if you have [%s] in your command.";

    /**
     * Constructs an MissingArgumentException instance that handles command with missing argument(s).
     *
     * @param commandPrefix The prefix used to identify the type of command.
     * @param hint The hint message to inform user potential missing arguments.
     */
    public MissingArgumentException(String commandPrefix, String hint) {
        super(String.format(errorMessage, commandPrefix, hint));
    }
}
