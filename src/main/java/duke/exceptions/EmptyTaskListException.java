package duke.exceptions;

public class EmptyTaskListException extends DukeException {
    /**
     * Occurs when Duke tries to clear the task list but it is already empty.
     */
    public EmptyTaskListException() {
        super("Hey! Youw can't clwear a task wist that is already emptwy!");
    }
}
