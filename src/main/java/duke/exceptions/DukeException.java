package duke.exceptions;

/**
 * Encapsulates the exceptions thrown by the Duke bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message message to be shown when DukeException is thrown.
     */
    public DukeException(String message) {
        super(message + "\nTo access the help page, type \"help\"");
    }
}
