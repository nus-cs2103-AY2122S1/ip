package seedu.duke.task;

/**
 * Represents a task. A <code>Task</code> is described by
 * a string description.
 */
public class Task {
    /**
     * String to describe the <code>Task</code> object.
     */
    private String description;

    /**
     * boolean to keep track if <code>Task</code> object
     * is completed.
     */
    private boolean isCompleted;

    /**
     * Public constructor for creating a <code>Task</code> object.
     *
     * @param description Description of the <code>Task</code> object created.
     */
    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    /**
     * Method to mark <code>Task</code> object as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * String representation of the <code>Task</code> object.
     *
     * @return String The string describing the <code>Task</code> object
     */
    @Override
    public String toString() {
        char marked = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", marked, this.description);
    }
}
