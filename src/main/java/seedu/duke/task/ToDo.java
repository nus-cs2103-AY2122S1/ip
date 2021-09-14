package seedu.duke.task;

/**
 * Represents a ToDo task. A <code>ToDo</code> describes
 * the task to be done.
 */
public class ToDo extends Task {

    /**
     * Public constructor to create a <code>ToDo</code>
     * object.
     *
     * @param description Description of what to do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Public constructor for creating a <code>ToDo</code> object.
     *
     * @param description Description of the <code>Task</code> object created.
     * @param isCompleted Boolean indicating if task is completed.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Mark <code>Task</code> object as completed.
     */
    public Task markAsCompleted() {
        return new ToDo(super.description, true);
    }

    /**
     * String representation of a <code>ToDo</code>.
     *
     * @return String representation of a <code>ToDo</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
