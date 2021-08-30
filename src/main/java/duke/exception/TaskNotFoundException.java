package duke.exception;

/**
 * Thrown when user inputs a task number that is out of range.
 *
 * @author Zhang Shi Chen
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("There is no such tasks with this task number!");
    }
}
