package duke.exception;

/**
 * Represents an exception when the user uses a command wrongly, such as providing inadequate arguments.
 */
public class InvalidCommandUsageException extends DukeException {
    private static final String EXCEPTION_MESSAGE_FORMAT = "Invalid use of the '%s' command.\n\n%s";

    public InvalidCommandUsageException(String commandWord, String properUsageMessage) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, commandWord, properUsageMessage));
    }
}
