package duke.exceptions;

/**
 * Class that handles cases where the find command
 * is unable to find a matching task in taskList.
 */
public class NoTaskMatchException extends DukeException {

    /**
     * Throws a standard error when unable to find
     * task in taskList.
     */
    public NoTaskMatchException() {
        super("You have no task matching this description!");
    }
}
