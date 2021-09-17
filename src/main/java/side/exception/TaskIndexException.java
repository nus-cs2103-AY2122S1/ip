package side.exception;

/**
 * TaskIndexException is thrown when user attempts to do a task requiring index without a valid index.
 *
 * @author Lua Yi Da
 */

public class TaskIndexException extends SideException {

    /**
     * Initialises a TaskIndexException.
     */
    public TaskIndexException() {
        super("No such task, more energy wasted...");
    }
}
