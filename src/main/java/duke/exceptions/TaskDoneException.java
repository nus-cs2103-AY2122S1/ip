package duke.exceptions;

/**
 * Class that handles task is already done exceptions.
 */
public class TaskDoneException extends DukeException {
    /**
     * Occurs when user tries to mark a task that's done but it's already done.
     */
    public TaskDoneException() {
        super("Looksw wike thisw taskw is alweady done! That's gweat!\n");
    }
}