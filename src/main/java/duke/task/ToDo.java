package duke.task;

import duke.task.Task;

/**
 * The duke.task.ToDo class encapsulates a duke.task to be done by the user.
 */
public class ToDo extends Task {
    /**
     * Constructor for the duke.task.ToDo class.
     *
     * @param description The description of the duke.task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for the duke.task.ToDo class.
     *
     * @param description The description of the duke.task.
     * @param isDone A boolean indicating whether the duke.task has been completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the duke.task.
     *
     * @return A string representing the duke.task.ToDo duke.task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveFormat() {
        return "T|" + super.getSaveFormat() + '\n';
    }
}
