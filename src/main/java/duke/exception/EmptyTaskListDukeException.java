package duke.exception;

/**
 * EmptyTaskListDukeException class.
 * Activated when list is called on a empty task list.
 */
public class EmptyTaskListDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "It seems that your task list is empty.\n"
            + "Try adding some task using \"todo\", \"deadline\" or \"event\"";

    /**
     * EmptyTaskListDukeException constructor.
     */
    public EmptyTaskListDukeException() {
        super(ERROR_MESSAGE);
    }
}
