package duke;

/**
 * Represents a general exception from Duke.
 */
public class DukeException extends Exception {
    public static final String INVALID_COMMAND_MESSAGE = "You anyhow one right!!";
    public static final String NO_COMMAND_ERROR = INVALID_COMMAND_MESSAGE
            + "\n\nIf you need any help, please enter:\n\n"
            + List.HELP_COMMAND;

    public String getMessage() {
        return NO_COMMAND_ERROR;
    }
}
