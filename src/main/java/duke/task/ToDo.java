package duke.task;

/**
 * Encapsulates the representation of a todo task.
 * The task has a description of what the user wants to do.
 *
 * @author Zhi Bin
 * @version Duke Level 8
 */
public class ToDo extends Task {

    /**
     * Constructor of a ToDo task.
     *
     * @param description The description of the task to be done.
     * @param isDone      Boolean representing if task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, 'T');
    }

}
