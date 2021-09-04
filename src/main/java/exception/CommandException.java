package exception;

/**
 * The CommandException Exception is thrown when a command after "/" is invalid.
 */
public class CommandException extends DukeException {

    /**
     * Constructs a new CommandException.
     *
     * @param task task to be added.
     * @param command command required.
     */
    public CommandException(String task, String command) {
        super(String.format("Your task of %s requires the command %s", task, command));
    }
}
