package duke;

/**
 * Represents a general exception from Duke.
 */
public class DukeException extends Exception {

    public static final String NO_COMMAND_ERROR = "You anyhow one right!!";

    public String getMessage() {
        return NO_COMMAND_ERROR;
    }
}
